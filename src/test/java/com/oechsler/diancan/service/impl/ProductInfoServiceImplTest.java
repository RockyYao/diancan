package com.oechsler.diancan.service.impl;

import com.oechsler.diancan.entity.ProductInfo;
import com.oechsler.diancan.service.ProductInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoService service;
    @Test
    public void findAll() throws Exception {

        PageRequest request=new PageRequest(2,2);
        Page<ProductInfo> productInfoPage= service.findAll(request);
        System.out.println(productInfoPage.getTotalElements());


    }

}