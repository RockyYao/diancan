package com.oechsler.diancan.repository;

import com.oechsler.diancan.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository <ProductCategory,Integer>{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categortTypeList);

}
