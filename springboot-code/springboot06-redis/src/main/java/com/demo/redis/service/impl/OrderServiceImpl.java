package com.demo.redis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.redis.mapper.OrderMapper;
import com.demo.redis.pojo.Coupon;
import com.demo.redis.pojo.Order;
import com.demo.redis.service.CouponService;
import com.demo.redis.service.OrderService;
import com.demo.redis.utils.RedisIdWorker;
import com.demo.redis.utils.Result;
import com.demo.redis.utils.SimpleRedisLock;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.demo.redis.constants.RedisConstants.CACHE_ORDER_KEY;
import static com.demo.redis.constants.RedisConstants.LOCK_ORDER_TTL;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private CouponService couponService;

    @Autowired
    private RedisIdWorker redisIdWorker;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result battle(Long couponId, Long userId) {
        Coupon coupon = couponService.getById(couponId);
        if (LocalDateTime.now().isBefore(coupon.getBeginTime())) {
            return Result.fail("活动尚未开始");
        }
        if (LocalDateTime.now().isAfter(coupon.getEndTime())) {
            return Result.fail("活动已经结束");
        }
        if (coupon.getNum() < 1) {
            return Result.fail("库存不足");
        }

        //单服务器解决：synchronized (userId.toString().intern())
        //分布式集群：redis nx 互斥锁
        SimpleRedisLock lock = new SimpleRedisLock(CACHE_ORDER_KEY + userId, stringRedisTemplate);
        boolean isLock = lock.tryLock(LOCK_ORDER_TTL);
        if (!isLock) {
            //获取锁失败
            return Result.fail("不允许重复下单");
        }
        try {
            //获取代理对象
            OrderService orderService = (OrderService) AopContext.currentProxy();
            return orderService.createOrder(couponId, userId, coupon);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 单服务器
     */
    @Transactional
    public Result createOrder(Long couponId, Long userId, Coupon coupon) {
        //一人一单
        Long existCount = lambdaQuery().eq(Order::getUserId, userId).eq(Order::getCouponId, couponId).count();
        if (existCount > 0) {
            return Result.fail("您已经领取过了");
        }
        //扣减库存
        boolean success = couponService.update()
                .setSql("num = num -1")
                .eq("id", couponId)
                .gt("num", 0)
                .update();
        if (!success) {
            return Result.fail("库存不足");
        }
        //创建订单
        Order order = new Order();
        long orderId = redisIdWorker.generateId("order");
        order.setId(orderId);
        order.setUserId(userId);
        order.setCouponId(couponId);
        save(order);
        return Result.ok(orderId);

    }

}
