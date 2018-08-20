package com.oechsler.diancan.controller;
import com.oechsler.diancan.convertor.OrderForm2OrderDTOConverter;
import com.oechsler.diancan.dto.OrderDto;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import com.oechsler.diancan.from.OrderFrom;
import com.oechsler.diancan.service.BuyerService;
import com.oechsler.diancan.service.OrderService;
import com.oechsler.diancan.util.ResultVoUtil;
import com.oechsler.diancan.vo.ResultVo;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author rocky
 * 买家订单API
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
@Api(basePath = "/sell/buyer/order", value = "BuyOrder-api", description = "微信点餐", produces = "application/json")
public class BuyOrderController {


    @Autowired
    private OrderService orderService;
    @Autowired
    private BuyerService buyerService;
    //创建订单
    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResultVo<Map<String,String>> create(@Valid OrderFrom orderFrom, BindingResult bindingResult){

        //表单校验
        if (bindingResult.hasErrors()){
            log.error("【创建订单】表单验证异常，参数不正确，orderFrom={}",orderFrom);

            throw new SellException(ResultEnum.PARAM_ERROR,bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto=OrderForm2OrderDTOConverter.convert(orderFrom);

        if (CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }

        OrderDto createResult=orderService.creat(orderDto);

        Map<String,String> map=new ConcurrentHashMap<>();
        map.put("orderId",createResult.getOrderId());
        return ResultVoUtil.success(map);

    }

    //订单列表
    @GetMapping("/list")
    public ResultVo<List<OrderDto>> list(@RequestParam("openid") String openid,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){

        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request=new PageRequest(page,size);
        Page<OrderDto> orderDtoPage=orderService.findList(openid,request);
        return ResultVoUtil.success(orderDtoPage.getContent());
    }

    //订单详情

    @GetMapping("/detail")
    public ResultVo<OrderDto> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderId") String orderid){

        OrderDto orderDto=  buyerService.findOrderOne(openid,orderid);

        return ResultVoUtil.success(orderDto);

    }

    //取消订单

    @GetMapping("/cancel")
    public ResultVo cancel(@RequestParam("openid") String openid,
                           @RequestParam("orderId") String orderid){

        buyerService.cancelOrder(openid,orderid);
        return ResultVoUtil.success();

    }



}
