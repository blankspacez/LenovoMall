package com.mall.lenovoMall.vo;

import com.mall.lenovoMall.entity.SeckillProduct;
import lombok.Data;

import java.io.Serializable;

@Data
public class SeckillProductVo extends SeckillProduct implements Serializable {

    private String productName;

    private Double productPrice;

    private String productPicture;

    private Long startTime;

    private Long endTime;


}
