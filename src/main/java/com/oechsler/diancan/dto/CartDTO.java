package com.oechsler.diancan.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author rocky
 */
@Data
@ToString
public class CartDTO {

    //商品ID
    private String productId;

    //商品数量
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public CartDTO() {
    }
}
