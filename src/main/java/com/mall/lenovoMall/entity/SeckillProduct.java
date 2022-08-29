package com.mall.lenovoMall.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "seckill_product")
public class SeckillProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Integer seckillId;

    private Integer productId;

    private Double seckillPrice;

    private Integer seckillStock;

    private Integer timeId;
}
