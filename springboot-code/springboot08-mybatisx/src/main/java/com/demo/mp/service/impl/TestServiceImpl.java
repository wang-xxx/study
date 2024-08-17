package com.demo.mp.service.impl;

import com.demo.mp.entity.User;
import com.demo.mp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Service
public class TestServiceImpl {

    @Autowired
    private UserMapper userMapper;

    @Transactional
    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        user.setName("张三" + new Random().nextInt(100));
        userMapper.updateById(user);

        throw new RuntimeException();
    }

}
