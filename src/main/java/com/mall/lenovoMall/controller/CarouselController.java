package com.mall.lenovoMall.controller;

import com.mall.lenovoMall.entity.Carousel;
import com.mall.lenovoMall.service.CarouselService;
import com.mall.lenovoMall.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarouselController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private CarouselService carouselService;

    @GetMapping("/resources/carousel")
    public ResultMessage carousels() {
        List<Carousel> carousels = carouselService.getCarouselList();
        resultMessage.success("001", carousels);
        return resultMessage;
    }

}
