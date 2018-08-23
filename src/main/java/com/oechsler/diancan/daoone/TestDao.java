package com.oechsler.diancan.daoone;

import com.oechsler.diancan.pojoone.Test;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 多数据源测试
 */
public interface TestDao extends JpaRepository<Test,Integer> {
}
