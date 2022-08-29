package com.mall.lenovoMall.controller;

import com.mall.lenovoMall.service.OrderService;
import com.mall.lenovoMall.util.ResultMessage;
import com.mall.lenovoMall.vo.CartVo;
import com.mall.lenovoMall.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private ResultMessage resultMessage;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResultMessage addOrder(@RequestBody List<CartVo> cartVoList, @CookieValue("XM_TOKEN") String cookie) {
        // 先判断cookie是否存在，和redis校验
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        orderService.addOrder(cartVoList, userId);
        resultMessage.success("001", "下单成功");
        return resultMessage;
    }

    @GetMapping("")
    public ResultMessage getOrder(@CookieValue("XM_TOKEN") String cookie) {
        // 先判断cookie是否存在，和redis校验
        Integer userId = (Integer) redisTemplate.opsForHash().get(cookie, "userId");
        List<List<OrderVo>> orders = orderService.getOrder(userId);
        resultMessage.success("001", orders);
        return resultMessage;
    }

}
