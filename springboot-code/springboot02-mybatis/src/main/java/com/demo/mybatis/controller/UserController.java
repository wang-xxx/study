package com.demo.mybatis.controller;

import com.demo.mybatis.pojo.User;
import com.demo.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserById")
    public User getUserById(Integer id) {
        return userService.getUserById(id);
    }

}
