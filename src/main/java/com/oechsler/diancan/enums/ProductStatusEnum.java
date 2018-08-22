package com.oechsler.diancan.enums;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 商品状态
 * @author rocky
 * 2018-6-10
 */
@Getter
public enum  ProductStatusEnum implements CodeEnum {


    UP(0,"在架"),
    DOWN(1,"下架")
    ;


    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
