package com.oechsler.diancan.repository;

import com.oechsler.diancan.entity.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    Page<OrderMaster> findByByAndBuyerOpenid(String buyerOpenid, Pageable pageable);




}
