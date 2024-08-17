package com.demo.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.redis.pojo.Coupon;
import com.demo.redis.pojo.Order;
import com.demo.redis.utils.Result;

public interface OrderService extends IService<Order> {

    Result battle(Long couponId, Long userId);

    Result createOrder(Long couponId, Long userId, Coupon coupon);
}
