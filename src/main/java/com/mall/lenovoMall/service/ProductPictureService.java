package com.mall.lenovoMall.service;

import com.mall.lenovoMall.exception.ExceptionEnum;
import com.mall.lenovoMall.exception.XmException;
import com.mall.lenovoMall.mapper.ProductPictureMapper;
import com.mall.lenovoMall.entity.ProductPicture;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductPictureService {

    @Autowired
    private ProductPictureMapper productPictureMapper;

    public List<ProductPicture> getProductPictureByProductId(String productId) {
        ProductPicture picture = new ProductPicture();
        picture.setProductId(Integer.parseInt(productId));
        List<ProductPicture> list = null;
        try {
            list = productPictureMapper.select(picture);
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_PICTURE_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_PICTURE_ERROR);
        }
        return list;
    }
}
