package com.oechsler.diancan.service;
import com.oechsler.diancan.dto.CartDTO;
import com.oechsler.diancan.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author rocky
 * 商品
 */
public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     * 查询在货架的所有商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page <ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存

    void increaseStock(List<CartDTO> cartDTOS);


    //减库存

    void decreaseStock(List<CartDTO> cartDTOS);



}
