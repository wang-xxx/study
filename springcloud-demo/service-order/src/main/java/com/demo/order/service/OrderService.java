package com.demo.order.service;

import com.demo.feign.dto.Order;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {

    Order queryOrderById(Long id);

}
