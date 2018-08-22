package com.oechsler.diancan.enums;

import com.oechsler.diancan.util.EnumUtil;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
public enum OrderStatusEnum implements CodeEnum {


    NEW(0,"新订单"),
    FINSISHED(1,"完结"),
    CANCEL(2,"已取消");

    private Integer code;

    private String message;


    public void setCode(Integer code) {
        this.code = code;
    }

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static  OrderStatusEnum getOrderStatusEnmu(Integer code)
    {

        return EnumUtil.getByCode(code,OrderStatusEnum.class);

    }
}
