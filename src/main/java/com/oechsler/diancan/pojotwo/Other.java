package com.oechsler.diancan.pojotwo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Other {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

}
