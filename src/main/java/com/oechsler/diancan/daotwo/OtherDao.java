package com.oechsler.diancan.daotwo;

import com.oechsler.diancan.pojotwo.Other;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 多数据源测试
 */
public interface OtherDao extends JpaRepository<Other,Integer>{
}
