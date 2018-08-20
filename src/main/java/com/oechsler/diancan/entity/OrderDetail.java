package com.oechsler.diancan.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@DynamicUpdate
public class OrderDetail implements Serializable {

    @Id
    private String detailId;

    private String orderId;

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    /**商品数量 */
    private Integer productQuantity;

    private String productIcon;

    



}
