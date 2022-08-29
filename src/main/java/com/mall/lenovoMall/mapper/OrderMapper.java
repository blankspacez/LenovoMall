package com.mall.lenovoMall.mapper;

import com.mall.lenovoMall.entity.Order;
import com.mall.lenovoMall.vo.OrderVo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface OrderMapper extends Mapper<Order> {

    @Select("select `order`.*, product.product_name as productName, product.product_picture as productPicture " +
            "from `order`, product where `order`.product_id = product.product_id and `order`.user_id = #{userId}")
    List<OrderVo> getOrderVoByUserId(Integer userId);
}