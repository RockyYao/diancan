package com.oechsler.diancan.entity;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 类目表
 * @author  rocky
 */
@Entity
@DynamicUpdate/** 动态更新*/
@Data  /** lombok代替 get set**/
@ToString
public class ProductCategory implements Serializable{

    /** 类目ID**/
    @Id
    @GeneratedValue
    private Integer categoryId;
    /** 类目名称**/
    private String categoryName;

    /** 类目编号**/
    private Integer categoryType;

    private Date creatTime;

    private Date updateTime;


}
