package com.mall.lenovoMall.mapper;

import com.mall.lenovoMall.entity.Collect;
import com.mall.lenovoMall.entity.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface CollectMapper extends Mapper<Collect> {

    @Select("select product.* from product, collect where collect.user_id = #{userId} and collect.product_id = product.product_id")
    List<Product> getCollect(String userId);
}