package com.oechsler.diancan.service;

import com.oechsler.diancan.entity.SellerInfo;

public interface SellerService {
    /**
     * 卖家端通过OPENID查取卖家信息
     * @param openid
     * @return
     */
    SellerInfo findByOpenid(String openid);

}
