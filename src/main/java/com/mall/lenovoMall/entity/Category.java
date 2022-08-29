package com.mall.lenovoMall.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer categoryId;

    private String categoryName;

}