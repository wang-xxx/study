package com.demo.redis.controller;

import com.demo.redis.service.CouponService;
import com.demo.redis.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cuppon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @GetMapping("/test")
    @ResponseBody
    public Result test() {
        return Result.ok(null);
    }

}
