package com.mall.lenovoMall.vo;

import com.mall.lenovoMall.entity.Order;
import lombok.Data;

@Data
public class OrderVo extends Order {

    private String productName;

    private String productPicture;

}
