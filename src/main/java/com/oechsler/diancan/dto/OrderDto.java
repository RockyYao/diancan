package com.oechsler.diancan.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.oechsler.diancan.entity.OrderDetail;
import com.oechsler.diancan.enums.OrderStatusEnum;
import com.oechsler.diancan.enums.PayStatusEnum;
import com.oechsler.diancan.util.Date2LongUtil;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)//null值不返回
public class OrderDto {
    /** 订单ID*/
    private String orderID;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;
    /** 订单金额*/
    private BigDecimal orderAmout;
    /** 订单状态*/
    private Integer orderStatus=OrderStatusEnum.NEW.getCode();
    /** 支付状态*/
    private Integer payStatus=PayStatusEnum.WAIT.getCode();

    @JsonSerialize(using = Date2LongUtil.class)
    private Date createTime;
    @JsonSerialize(using = Date2LongUtil.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
