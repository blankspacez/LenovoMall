package com.mall.lenovoMall.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.lenovoMall.exception.ExceptionEnum;
import com.mall.lenovoMall.exception.XmException;
import com.mall.lenovoMall.mapper.ProductMapper;
import com.mall.lenovoMall.entity.Product;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> getProductByCategoryId(Integer categoryId) {
        List<Product> list = null;
        Example example = new Example(Product.class);
        example.orderBy("productSales").desc();
        example.createCriteria().andEqualTo("categoryId", categoryId);
        PageHelper.startPage(0, 7);
        try {
            list = productMapper.selectByExample(example);
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_ERROR);
        }
        return list;
    }

    public List<Product> getHotProduct() {
        Example example = new Example(Product.class);
        example.orderBy("productSales").desc();
        PageHelper.startPage(0, 7);
        List<Product> list = null;
        try {
            list = productMapper.selectByExample(example);
            if (ArrayUtils.isEmpty(list.toArray())) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_ERROR);
        }
        return list;
    }

    public Product getProductById(Integer productId) {
        Product product = null;
        try {
            product = productMapper.getProductById(productId);
            if (product == null) {
                throw new XmException(ExceptionEnum.GET_PRODUCT_NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new XmException(ExceptionEnum.GET_PRODUCT_ERROR);
        }
        return product;
    }

    public PageInfo<Product> getProductByPage(String currentPage, String pageSize, String categoryId) {
        List<Product> list = null;
        PageHelper.startPage(Integer.parseInt(currentPage), Integer.parseInt(pageSize), true).setOrderBy("product_id asc");
        if (categoryId.equals("0")) { // 为0，代表分页查询所有商品
            list = productMapper.selectAll();
        }else {
            // 分类分页查询商品
            Product product = new Product();
            product.setCategoryId(Integer.parseInt(categoryId));
            list = productMapper.select(product);
        }
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        return pageInfo;
    }

    public PageInfo<Product> getProductBySearch(String search, String currentPage, String pageSize){
        List<Product> list = null;
        PageHelper.startPage(Integer.parseInt(currentPage) - 1, Integer.parseInt(pageSize), true);
        // 关键字搜索模糊查询商品
        list = productMapper.fuzzySearch(search);
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        return pageInfo;
    }
}
