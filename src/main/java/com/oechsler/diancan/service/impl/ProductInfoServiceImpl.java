package com.oechsler.diancan.service.impl;

import com.oechsler.diancan.dto.CartDTO;
import com.oechsler.diancan.entity.ProductInfo;
import com.oechsler.diancan.enums.ProductStatusEnum;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import com.oechsler.diancan.repository.ProductInfoRepository;
import com.oechsler.diancan.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoRepository productInfoRepository;
    @Override
    public ProductInfo findOne(String productId) {
        return productInfoRepository.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOS) {

        for (CartDTO cartDTO:cartDTOS){
            ProductInfo productInfo=productInfoRepository.findOne(cartDTO.getProductId());
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);
            }
            Integer result=productInfo.getProductStock()+cartDTO.getProductQuantity();
            productInfo.setProductStock(result);
            productInfoRepository.save(productInfo);

        }

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOS) {
        for (CartDTO cartDTO:cartDTOS){
          ProductInfo productInfo=  productInfoRepository.findOne(cartDTO.getProductId());

          if (productInfo==null){

              throw new SellException(ResultEnum.PRODUCT_NOT_EXIT);

          }
            Integer result= productInfo.getProductStock()- productInfo.getProductStock();
          if (result<0){
              throw new SellException(ResultEnum.PRODUCT_STOCK_NOT_EXIT);
          }

          productInfo.setProductStock(result);
          productInfoRepository.save(productInfo);
        }

    }
}
