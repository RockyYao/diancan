package com.oechsler.diancan.service;

import com.oechsler.diancan.entity.ProductCategory;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * 类目
 */
public interface ProductCategoryService {

    ProductCategory findOne(Integer id);

    List<ProductCategory> findAll();

    ProductCategory save(ProductCategory productCategory);

    List<ProductCategory> findCategoryTypeIn(List<Integer> categoryTypeList);


}
