package com.mall.lenovoMall.mapper;

import com.mall.lenovoMall.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ProductMapper extends Mapper<Product> {

    @Select("select product_id from product")
    List<Integer> selectIds();

    @Select("select * from product where product_id= ${ id }")
    Product getProductById(@Param("id") Integer id);

    @Select("select * from product where product_name like \"%${ search }%\" or product_title like \"%${ search }%\" or product_intro like \"%${ search }%\"")
    List<Product> fuzzySearch(@Param("search") String search);
}