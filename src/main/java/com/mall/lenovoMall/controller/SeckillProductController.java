package com.mall.lenovoMall.controller;

import com.mall.lenovoMall.entity.SeckillProduct;
import com.mall.lenovoMall.entity.SeckillTime;
import com.mall.lenovoMall.service.SeckillProductService;
import com.mall.lenovoMall.util.ResultMessage;
import com.mall.lenovoMall.vo.SeckillProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seckill/product")
public class SeckillProductController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private SeckillProductService seckillProductService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据时间id获取对应时间的秒杀商品列表
     * @param timeId
     * @return
     */
    @GetMapping("/time/{timeId}")
    public ResultMessage getProduct(@PathVariable String timeId) {
        List<SeckillProductVo> seckillProductVos = seckillProductService.getProduct(timeId);
        resultMessage.success("001", seckillProductVos);
        return resultMessage;
    }

    /**
     * 获取秒杀商品
     * @param seckillId
     * @return
     */
    @GetMapping("/{seckillId}")
    public ResultMessage getSeckill(@PathVariable String seckillId) {
        SeckillProductVo seckillProductVo = seckillProductService.getSeckill(seckillId);
        resultMessage.success("001", seckillProductVo);
        return resultMessage;
    }

    /**
     * 获取时间段
     * @return
     */
    @GetMapping("/time")
    public ResultMessage getTime() {
        List<SeckillTime> seckillTimes = seckillProductService.getTime();
        resultMessage.success("001", seckillTimes);
        return resultMessage;
    }

    /**
     * 添加秒杀商品
     * @param seckillProduct
     * @return
     */
    @PostMapping("")
    public ResultMessage addSeckillProduct(@RequestBody SeckillProduct seckillProduct) {
        seckillProductService.addSeckillProduct(seckillProduct);
        resultMessage.success("001", "添加成功");
        return resultMessage;
    }

    /**
     * 开始秒杀
     * @param seckillId
     * @return
     */
    @PostMapping("/seckill/{seckillId}")
    public ResultMessage seckillProduct(@PathVariable String seckillId, @CookieValue("XM_TOKEN") String cookie) {
        // 先判断cookie是否存在，和redis校验
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        seckillProductService.seckillProduct(seckillId, userId);
        resultMessage.success("001", "排队中");
        return resultMessage;
    }


}
