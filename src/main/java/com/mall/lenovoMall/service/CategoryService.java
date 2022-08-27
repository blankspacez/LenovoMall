package com.mall.lenovoMall.service;

import com.mall.lenovoMall.exception.ExceptionEnum;
import com.mall.lenovoMall.exception.XmException;
import com.mall.lenovoMall.mapper.CategoryMapper;
import com.mall.lenovoMall.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> getAll() {
        List<Category> categories = null;
        try {
            categories = categoryMapper.selectAll();
            if (categories == null) {
                throw new XmException(ExceptionEnum.GET_CATEGORY_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_CATEGORY_ERROR);
        }
        return categories;
    }
}
