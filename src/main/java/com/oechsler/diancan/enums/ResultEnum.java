package com.oechsler.diancan.enums;

import lombok.Getter;

@Getter
public enum  ResultEnum {

    SUCCESS(0,"成功"),
    PARAM_ERROR(1,"参数异常"),
    PRODUCT_NOT_EXIT(10,"商品不存在"),
    PRODUCT_STOCK_NOT_EXIT(11,"商品库存不够"),
    ORDER_NOT_EXIT(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIT(13,"订单详情不存在"),
    ORDERID_IS_NULL(14,"订单ID为空"),
    ORDER_STATUS_ERROR(15,"订单状态错误"),
    ORDER_UPADTE_FAIL(16,"订单更新失败"),
    ORDER_DETAIL_EMPTY(17,"订单中无商品"),
    ORDER_PAY_FAIL(18,"订单支付错误"),
    CART_EMPTY(19,"购物车不能为空"),
    ORDER_OWNER_ERROR(20,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(21,"微信授权错误"),
    PRODUCT_STATUS_ERROR(22,"商品状态错误")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
