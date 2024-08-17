package com.demo.skywalking.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.skywalking.mapper.UserMapper;
import com.demo.skywalking.pojo.User;
import com.demo.skywalking.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Transactional(propagation = REQUIRED)
    void test() {

    }

}