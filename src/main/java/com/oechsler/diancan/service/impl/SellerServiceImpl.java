package com.oechsler.diancan.service.impl;

import com.oechsler.diancan.entity.SellerInfo;
import com.oechsler.diancan.repository.SellerInfoRepository;
import com.oechsler.diancan.service.SellerService;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {

  private SellerInfoRepository sellerInfoRepository;

    @Override
    public SellerInfo findByOpenid(String openid) {
       return  sellerInfoRepository.findByOpenid(openid);
    }
}
