package com.oechsler.diancan.enums;

import com.oechsler.diancan.util.EnumUtil;
import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public static PayStatusEnum getPaySyayusEnum(Integer code){

        return EnumUtil.getByCode(code,PayStatusEnum.class);
    }

}
