package com.demo.order.controller;

import com.demo.feign.dto.Order;
import com.demo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{orderId}")
    public Order queryOrder(@RequestHeader(value = "userInfo", required = false) String userInfo, @PathVariable("orderId") Long orderId) {
        return orderService.queryOrderById(orderId);
    }

}
