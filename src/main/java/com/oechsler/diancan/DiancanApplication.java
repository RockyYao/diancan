package com.oechsler.diancan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@MapperScan("com.oechsler.diancan.entity.mapper")//Mapper扫描
public class DiancanApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiancanApplication.class, args);
	}
}
