package com.oechsler.diancan.service;

import com.oechsler.diancan.dto.OrderDto;

/**
 * 验证是否本人操作
 */
public interface BuyerService {

    /**
     * 查询一个订单
     * @param openId
     * @param orderId
     * @return
     */
    OrderDto findOrderOne(String openId,String orderId);

    /**
     * 取消一个订单
     * @param openId
     * @param orderId
     * @return
     */
    OrderDto cancelOrder(String openId,String orderId);

}
