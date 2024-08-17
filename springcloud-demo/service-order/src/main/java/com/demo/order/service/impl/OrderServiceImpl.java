package com.demo.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.feign.clients.UserClient;
import com.demo.feign.dto.Order;
import com.demo.feign.dto.User;
import com.demo.order.mapper.OrderMapper;
import com.demo.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    @Override
    public Order queryOrderById(Long id) {
        Order order = this.getById(id);
        if (order == null) {
            return null;
        }
        //RestTemplate调用服务
        //String url = "http://localhost:8081/user/" + order.getUserId();
        //String url = "http://service-user/user/" + order.getUserId();
        //User user = restTemplate.getForObject(url, User.class);

        //Feign调用服务
        User user = userClient.findById(order.getUserId());

        order.setUser(user);
        return order;
    }
}




