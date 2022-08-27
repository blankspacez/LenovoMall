package com.mall.lenovoMall.controller;

import com.mall.lenovoMall.entity.Category;
import com.mall.lenovoMall.service.CategoryService;
import com.mall.lenovoMall.util.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResultMessage category() {
        List<Category> categories = categoryService.getAll();
        resultMessage.success("001", categories);
        return resultMessage;
    }

}
