package com.oechsler.diancan.service.impl;

import com.oechsler.diancan.dto.OrderDto;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import com.oechsler.diancan.service.BuyerService;
import com.oechsler.diancan.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;
    @Override
    public OrderDto findOrderOne(String openId, String orderId) {
        OrderDto orderDto=orderService.findOne(orderId);
        if (orderDto==null){
            return null;
        }
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openId)){
           log.error("订单不是本人操作");
           throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }

        return orderDto;
    }

    @Override
    public OrderDto cancelOrder(String openId, String orderId) {
        OrderDto orderDto=orderService.findOne(orderId);
        if (orderDto==null){
           log.error("【取消订单】订单不存在");
           throw new SellException(ResultEnum.ORDER_NOT_EXIT);
        }
        if(!orderDto.getBuyerOpenid().equalsIgnoreCase(openId)) {
            log.error("订单不是本人操作");
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        orderService.cancel(orderDto);
        return orderDto;

    }
}
