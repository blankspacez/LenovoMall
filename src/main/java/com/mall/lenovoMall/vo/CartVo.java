package com.mall.lenovoMall.vo;

import lombok.Data;

@Data
public class CartVo {

    private Integer id;

    private Integer productId;

    private String productName;

    private String productImg;

    private Double price;

    private Integer num;

    private Integer maxNum;

    private boolean check;
}
