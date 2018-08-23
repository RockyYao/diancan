package com.oechsler.diancan.entity.mapper;

import com.oechsler.diancan.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Mapper方式
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryMapperTest {


    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    //map
    @Test
    public void insertBymap() throws Exception {


        Map<String,Object> map=new HashMap<>();
        map.put("category_name","师兄不爱");
        map.put("category_type",101);
        int result=productCategoryMapper.insertBymap(map);
        Assert.assertEquals(1,result);
    }
    //object
    @Test
    public void insertByObject() throws Exception {
        ProductCategory category=new ProductCategory();
        category.setCategoryType(105);
        category.setCategoryName("师姐最爱");
        int result=productCategoryMapper.insertByObject(category);
        Assert.assertEquals(1,result);


    }
}