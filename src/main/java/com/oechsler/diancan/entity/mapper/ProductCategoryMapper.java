package com.oechsler.diancan.entity.mapper;

import com.oechsler.diancan.entity.ProductCategory;
import org.apache.ibatis.annotations.Insert;

import java.util.Map;

public interface ProductCategoryMapper {


    @Insert("insert into product_category(category_name,category_type) values(#{category_name,jdbcType=VARCHAR},#{category_type,jdbcType=INTEGER})")
    int insertBymap(Map<String,Object> map);
    @Insert("insert into product_category(category_name,category_type) values(#{categoryName,jdbcType=VARCHAR},#{categoryType,jdbcType=INTEGER})")
    int insertByObject(ProductCategory category);

}
