package com.oechsler.diancan.service.impl;

import com.oechsler.diancan.entity.ProductCategory;
import com.oechsler.diancan.service.ProductCategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)

public class ProductCategoryServiceImplTest {


    @Autowired
    private ProductCategoryService service;

    @Test
    public void findOne() throws Exception {
        ProductCategory category=service.findOne(1);
        assertNotNull(category);

    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> category=service.findAll();
        assertNotNull(category);

    }

    @Test
   @org.springframework.transaction.annotation.Transactional
    public void save() throws Exception {
        ProductCategory productCategory=new ProductCategory();
        productCategory.setCategoryName("1");
        productCategory.setCategoryType(2);
        ProductCategory category=service.save(productCategory);
        assertNotNull(category);

    }

    @Test
    public void findCategoryTypeIn() throws Exception {
        List list=new ArrayList();
        list.add(12);
        list.add(5);
        list.add(3);

        List<ProductCategory> categories=service.findCategoryTypeIn(list);
        assertNotNull(categories);

    }

}