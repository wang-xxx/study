package com.demo.redis.controller;

import com.demo.redis.service.OrderService;
import com.demo.redis.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/battle/{couponId}/{userId}")
    @ResponseBody
    public Result battle(@PathVariable Long couponId, @PathVariable Long userId) {
        return orderService.battle(couponId, userId);
    }

}
