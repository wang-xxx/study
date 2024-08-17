package com.demo.redis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.redis.pojo.User;
import com.demo.redis.utils.Result;

import java.util.List;

public interface UserService extends IService<User> {

    List<User> list();

    Result getUserById(Long id);

}
