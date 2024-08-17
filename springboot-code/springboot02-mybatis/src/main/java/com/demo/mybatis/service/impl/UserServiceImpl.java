package com.demo.mybatis.service.impl;

import com.demo.mybatis.mapper.UserMapper;
import com.demo.mybatis.pojo.User;
import com.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

}
