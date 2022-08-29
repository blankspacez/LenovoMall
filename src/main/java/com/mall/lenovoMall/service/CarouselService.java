package com.mall.lenovoMall.service;

import com.mall.lenovoMall.exception.ExceptionEnum;
import com.mall.lenovoMall.exception.XmException;
import com.mall.lenovoMall.mapper.CarouselMapper;
import com.mall.lenovoMall.entity.Carousel;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    public List<Carousel> getCarouselList() {
        List<Carousel> list = null;
        try {
            list = carouselMapper.selectAll();
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_CAROUSEL_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_CAROUSEL_ERROR);
        }
        return list;
    }

}
