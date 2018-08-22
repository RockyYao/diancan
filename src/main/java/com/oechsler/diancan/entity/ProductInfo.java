package com.oechsler.diancan.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oechsler.diancan.enums.ProductStatusEnum;
import com.oechsler.diancan.util.EnumUtil;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 * @author rocky
 * 2018-5-30
 */
@DynamicUpdate
@Entity
@Data
@ToString
public class ProductInfo implements Serializable{
    @Id
    private String productId;
    /**  名字  */
    private String productName;
    /**  单价  */
    private BigDecimal productPrice;
    /**  库存  */
    private int productStock;
    /**  描述  */
    private String productDescription;
    /**  小图  */
    private String productIcon;
    /**  类目编号  */
    private Integer categoryType;
    /** 商品状态 0正常1下架*/
    private Integer productStatus;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum(){

        return EnumUtil.getByCode(productStatus,ProductStatusEnum.class);

    }
}
