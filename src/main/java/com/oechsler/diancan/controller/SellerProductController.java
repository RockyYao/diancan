package com.oechsler.diancan.controller;

import com.oechsler.diancan.dto.OrderDto;
import com.oechsler.diancan.entity.ProductInfo;
import com.oechsler.diancan.enums.ProductStatusEnum;
import com.oechsler.diancan.enums.ResultEnum;
import com.oechsler.diancan.exception.SellException;
import com.oechsler.diancan.service.ProductInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {

    @Autowired
    private ProductInfoService productInfoService;
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        PageRequest pageRequest=new PageRequest(page-1,size);

        Page<ProductInfo> productInfoPage = productInfoService.findAll(pageRequest);

        List<ProductInfo> productInfoList=productInfoPage.getContent();
        map.put("productInfoList",productInfoList);

        map.put("productInfoPage",productInfoPage);
        map.put("currentPage",page);

        return new ModelAndView("product/list",map);

    }



    @GetMapping("/down")
    public ModelAndView down(@RequestParam("productId") String productId, Map<String, Object> map){

        try {
            ProductInfo productInfo = productInfoService.findOne(productId);

            if (productInfo.getProductStatus().equals(ProductStatusEnum.UP.getCode())){
                productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
                productInfoService.save(productInfo);
            }else {
                log.error("【商品下架错误】productId={}",productId);
                map.put("msg", ResultEnum.PRODUCT_STATUS_ERROR.getMessage());
                map.put("url","/sell/seller/product/list");
                return new ModelAndView("common/error",map);
            }

        }catch (SellException e){
           log.error("【商品下架错误】productId={}",productId);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }


    @GetMapping("/up")
    public ModelAndView up(@RequestParam("productId") String productId, Map<String, Object> map){

        try {
            ProductInfo productInfo = productInfoService.findOne(productId);
            if (productInfo.getProductStatus().equals(ProductStatusEnum.DOWN.getCode())){
                productInfo.setProductStatus(ProductStatusEnum.UP.getCode());
                productInfoService.save(productInfo);
            }else {
            log.error("【商品下架错误】productId={}",productId);
            map.put("msg", ResultEnum.PRODUCT_STATUS_ERROR.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        }catch (SellException e){
            log.error("【商品下架错误】productId={}",productId);
            map.put("msg", e.getMessage());
            map.put("url","/sell/seller/product/list");
            return new ModelAndView("common/error",map);
        }
        map.put("msg", ResultEnum.SUCCESS.getMessage());
        map.put("url","/sell/seller/product/list");
        return new ModelAndView("common/success",map);
    }


}
