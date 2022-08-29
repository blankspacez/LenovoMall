package com.mall.lenovoMall.mapper;

import com.mall.lenovoMall.entity.SeckillProduct;
import com.mall.lenovoMall.vo.SeckillProductVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

@Repository
public interface SeckillProductMapper extends Mapper<SeckillProduct>, MySqlMapper<SeckillProduct> {

    @Select("select seckill_time.start_time, seckill_time.end_time, seckill_product.*, product.product_name, product.product_price, product.product_picture from seckill_product ,product, seckill_time where seckill_product.time_id = seckill_time.time_id and seckill_product.product_id = product.product_id and seckill_product.time_id = #{timeId} and seckill_time.end_time > #{time}")
    List<SeckillProductVo> getSeckillProductVos(String timeId, Long time);

    @Select("select seckill_time.start_time, seckill_time.end_time, seckill_product.*, product.product_name, product.product_price, product.product_picture from seckill_product ,product, seckill_time where seckill_product.time_id = seckill_time.time_id and seckill_product.product_id = product.product_id and seckill_product.seckill_id = #{seckillId}")
    SeckillProductVo getSeckill(String seckillId);

    @Update("update seckill_product set seckill_stock = seckill_stock - 1 where seckill_id = #{seckillId} and seckill_stock > 0")
    void decrStock(Integer seckillId);

    @Delete("delete from seckill_product")
    void deleteAll();

}
