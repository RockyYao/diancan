package com.oechsler.diancan.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oechsler.diancan.entity.ProductInfo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品包含类目
 */
@Data
public class ProductInfoVo {

    @JsonProperty("id")
    private String productId;
    /**  名字  */
    @JsonProperty("name")
    private String productName;
    /**  单价  */
    @JsonProperty("price")
    private BigDecimal productPrice;
    /**  描述  */
    @JsonProperty("description")
    private String productDescription;
    /**  小图  */
    @JsonProperty("icon")
    private String productIcon;

}
