package com.oechsler.diancan.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductVo {
    /**类目名字*/
    private String name;
    /**类目编号*/
    private Integer type;

    private List<ProductInfoVo> foods;

}
