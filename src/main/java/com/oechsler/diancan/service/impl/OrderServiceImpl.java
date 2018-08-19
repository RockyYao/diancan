package com.oechsler.diancan.service.impl;
import com.oechsler.diancan.convertor.OrderMaster2OrderDTO;
import com.oechsler.diancan.dto.CartDTO;
import com.oechsler.diancan.dto.OrderDto;
import com.oechsler.diancan.entity.OrderDetail;
import com.oechsler.diancan.entity.OrderMaster;
import com.oechsler.diancan.entity.ProductInfo;
import com.oechsler.diancan.enums.OrderStatusEnum;
import com.oechsler.diancan.enums.PayStatusEnum;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import com.oechsler.diancan.repository.OrderDetailRepository;
import com.oechsler.diancan.repository.OrderMasterRepository;
import com.oechsler.diancan.service.OrderService;
import com.oechsler.diancan.service.ProductInfoService;
import com.oechsler.diancan.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {


    @Autowired
    private OrderDetailRepository detailRepository;
    @Autowired
    private ProductInfoService productInfoService;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderDto creat(OrderDto orderDto) {

        String orderId=KeyUtil.genUniqueKey();

        BigDecimal orderAmout=new BigDecimal(BigInteger.ZERO);
        //查询商品
        for (OrderDetail orderDetail:orderDto.getOrderDetailList()){
            ProductInfo productInfo=productInfoService.findOne(orderDetail.getProductId());
            if (productInfo==null){
                log.error("商品不存在 商品ID{}",productInfo.getProductId());
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            //计算订单总价
          orderAmout=orderDetail.getProductPrice()
                  .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                  .add(orderAmout);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductIcon(productInfo.getProductIcon());
            orderDetail.setProductName(productInfo.getProductName());
            orderDetail.setProductPrice(productInfo.getProductPrice());
            detailRepository.save(orderDetail);
        }

        //写入订单数据
        OrderMaster orderMaster=new OrderMaster();
        orderMaster.setOrderAmout(orderAmout);
        orderMaster.setOrderID(orderId);
        orderMaster.setBuyerAddress(orderDto.getBuyerAddress());
        orderMaster.setBuyerName(orderDto.getBuyerName());
        orderMaster.setBuyerOpenid(orderDto.getBuyerOpenid());
        orderMaster.setBuyerPhone(orderDto.getBuyerPhone());

        orderMasterRepository.save(orderMaster);
        //扣库存
        List<CartDTO> cartDTOS  =
                orderDto.getOrderDetailList().stream()
                        .map(e->new CartDTO(e.getProductId(),e.getProductQuantity()))
                        .collect(Collectors.toList());

        productInfoService.decreaseStock(cartDTOS);
        BeanUtils.copyProperties(orderMaster,orderDto);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster=orderMasterRepository.findOne(orderId);
        if (orderMaster==null){
            log.error("订单不存在 订单ID{}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        List<OrderDetail> orderDetails=detailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)){
            log.error("订单详细表不存在 订单ID{}",orderId);
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIT);
        }
        OrderDto orderDto=new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetails);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasters=orderMasterRepository.findByByAndBuyerOpenid(buyerOpenid,pageable);
        List<OrderDto> orderDtoList=OrderMaster2OrderDTO.convert(orderMasters.getContent());
        Page<OrderDto> orderDtos=new PageImpl<>(orderDtoList,pageable,orderMasters.getTotalElements());
        return orderDtos;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {

        if (orderDto.getOrderID()==null){
            log.error("订单ID为空对象{}",orderDto.toString());
            throw new SellException(ResultEnum.ORDERID_IS_NULL);
        }
        OrderMaster orderMaster=orderMasterRepository.findOne(orderDto.getOrderID());
        if (orderMaster==null){
            log.error("订单不存在 订单ID{}",orderDto.getOrderID());
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        BeanUtils.copyProperties(orderMaster,orderDto);

        //判断订单状态
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("OrderService.cancel订单结束状态不正确，orderId={}，orderStatus={}",orderDto.getOrderID(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if (updateResult==null){
            log.error("更新失败 orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPADTE_FAIL);
        }
        List<OrderDetail> orderDetails=detailRepository.findByOrderId(orderDto.getOrderID());
        //返回库存
        if (CollectionUtils.isEmpty(orderDetails)){
            log.error("【取消订单】订单中无商品详情,orderId={}",orderDto.getOrderID());
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }

        List<CartDTO> cartDTOS=orderDetails.stream()
                .map(e->new CartDTO(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());

        productInfoService.increaseStock(cartDTOS );

        //如果以支付，要退款

        if (orderMaster.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())){
            //TUDO
        }


        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {

        if (orderDto.getOrderID()==null){
            log.error("订单ID为空对象{}",orderDto.toString());
            throw new SellException(ResultEnum.ORDERID_IS_NULL);
        }

        OrderMaster orderMaster=orderMasterRepository.findOne(orderDto.getOrderID());
        if (orderMaster==null){
            log.error("订单不存在 订单ID{}",orderDto.getOrderID());
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        BeanUtils.copyProperties(orderMaster,orderDto);
        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("OrderService.cancel订单结束状态不正确，orderId={}，orderStatus={}",orderDto.getOrderID(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        orderMaster.setOrderStatus(OrderStatusEnum.FINSISHED.getCode());
        OrderMaster updateResult=orderMasterRepository.save(orderMaster);
        if (updateResult==null){
            log.error("更新失败 orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPADTE_FAIL);
        }
        orderDto.setOrderStatus(OrderStatusEnum.FINSISHED.getCode());
        return orderDto;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {

        if (orderDto.getOrderID()==null){
            log.error("订单ID为空对象{}",orderDto.toString());
            throw new SellException(ResultEnum.ORDERID_IS_NULL);
        }

        //判断订单状态
        OrderMaster orderMaster=orderMasterRepository.findOne(orderDto.getOrderID());

        if (orderMaster==null){
            log.error("订单不存在 订单ID{}",orderDto.getOrderID());
            throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        BeanUtils.copyProperties(orderMaster,orderDto);

        if (!orderMaster.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【订单支付】订单状态不正确，orderId={}，orderStatus={}",orderDto.getOrderID(),orderMaster.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if (!orderMaster.getPayStatus().equals(PayStatusEnum.WAIT))
        {
            log.error("【订单支付】订单支付状态不正确，orderMAster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_PAY_FAIL);
        }

        //修改支付状态
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster payResult= orderMasterRepository.save(orderMaster);
        if (payResult==null){
            log.error("【订单支付】订单支付错误");
            throw new SellException(ResultEnum.ORDER_PAY_FAIL);
        }
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        return orderDto;
    }
}
