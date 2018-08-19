package com.oechsler.diancan.controller;

import com.oechsler.diancan.entity.ProductCategory;
import com.oechsler.diancan.entity.ProductInfo;
import com.oechsler.diancan.service.ProductCategoryService;
import com.oechsler.diancan.service.ProductInfoService;
import com.oechsler.diancan.util.ResultVoUtil;
import com.oechsler.diancan.vo.ProductInfoVo;
import com.oechsler.diancan.vo.ProductVo;
import com.oechsler.diancan.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author rocky
 * 买家购买API
 */
@Slf4j
@RestController
@Api(basePath = "/sell", value = "BugProduct-api", description = "微信点餐", produces = "application/json")
public class BuyProductController {

    @Autowired
    private ProductInfoService infoService;
    @Autowired
    private ProductCategoryService categoryService;


    @ApiOperation(httpMethod = "GET", value = "[微信点餐]-界面类目商品返回", notes = "查询类目商品", response = ResultVo.class)
    @RequestMapping(value = "/buyer/product/list",method = RequestMethod.GET)
    public ResultVo list(){

        log.info("-----------------resultvo return------------------------");

        //1 查询所有上架的商品
        List<ProductInfo> productInfoList=infoService.findUpAll();
        //2查询类目
//        for (ProductInfo productInfo:productInfoList){
  //          cateGoryTypeList.add(productInfo.getCategoryType());}
        List<Integer>  cateGoryTypeList=
                productInfoList.stream().map(e->e.getCategoryType()).collect(Collectors.toList());

       List<ProductCategory>productCategories= categoryService.findCategoryTypeIn(cateGoryTypeList);

        List<ProductVo> productVoList=new ArrayList<>();
        for (ProductCategory productCategory :productCategories){
            ProductVo productVo=new ProductVo();
            productVo.setName(productCategory.getCategoryName());
            productVo.setType(productCategory.getCategoryType());
            List<ProductInfoVo> productInfoVoList=new ArrayList<>();

            for (ProductInfo productInfo:productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVo productInfoVo=new ProductInfoVo();
                    productInfoVo.setProductDescription(productInfo.getProductDescription());
                    productInfoVo.setProductIcon(productInfo.getProductIcon());
                    productInfoVo.setProductId(productInfo.getProductId());
                    productInfoVo.setProductName(productInfo.getProductName());
                    productInfoVo.setProductPrice(productInfo.getProductPrice());
                    productInfoVoList.add(productInfoVo);
                }
            }
            productVo.setFoods(productInfoVoList);

            productVoList.add(productVo);
        }

      return ResultVoUtil.success(productVoList);
        log.info("-----------------resultvo return over------------------------");
    }







}
