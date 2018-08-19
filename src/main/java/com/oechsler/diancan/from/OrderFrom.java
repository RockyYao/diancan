package com.oechsler.diancan.from;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * @author rocky
 */
@Data
@ToString
public class OrderFrom {

    /**
     * 卖家姓名
     */
    @NotEmpty(message = "姓名必填")
    private String name;
    /**
     * 手机
     */
    @NotEmpty(message = "手机必填")
    private String phone;
    /**
     * 地址
     */
    @NotEmpty(message = "地址必填")
    private String address;
    /**
     * openid
     */
    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
