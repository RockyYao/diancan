package com.oechsler.diancan.entity;

import com.oechsler.diancan.enums.OrderStatusEnum;
import com.oechsler.diancan.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster implements Serializable {

    /** 订单ID*/
    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;
    /** 订单金额*/
    private BigDecimal orderAmount;
    /** 订单状态*/
    private Integer orderStatus=OrderStatusEnum.NEW.getCode();
    /** 支付状态*/
    private Integer payStatus=PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;


}
