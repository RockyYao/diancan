package com.oechsler.diancan.entity;


import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
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
