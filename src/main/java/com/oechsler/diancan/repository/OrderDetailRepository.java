package com.oechsler.diancan.repository;

import com.oechsler.diancan.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {


    List<OrderDetail> findByOrderId(String orderId);


}




