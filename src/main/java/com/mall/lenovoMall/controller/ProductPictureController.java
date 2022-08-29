package com.mall.lenovoMall.controller;

import com.mall.lenovoMall.entity.ProductPicture;
import com.mall.lenovoMall.service.ProductPictureService;
import com.mall.lenovoMall.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/productPicture")
public class ProductPictureController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private ProductPictureService productPictureService;

    @GetMapping("/product/{productId}")
    public ResultMessage productPicture(@PathVariable String productId) {
        List<ProductPicture> products = productPictureService.getProductPictureByProductId(productId);
        resultMessage.success("001", products);
        return resultMessage;
    }

}
