package com.mall.lenovoMall.mapper;

import com.mall.lenovoMall.entity.SeckillTime;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SeckillTimeMapper extends Mapper<SeckillTime> {

    @Select("select * from seckill_time where end_time > #{time} limit 6")
    List<SeckillTime> getTime(long time);

    @Delete("delete from seckill_time")
    void deleteAll();

    @Select("select endTime from seckill_time where time_id = #{timeId}")
    Long getEndTime(Integer timeId);
}
