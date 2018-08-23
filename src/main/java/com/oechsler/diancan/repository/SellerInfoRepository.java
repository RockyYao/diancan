package com.oechsler.diancan.repository;


import com.oechsler.diancan.entity.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerInfoRepository extends JpaRepository<SellerInfo,String > {

    SellerInfo findByOpenid(String openid);


}
