package com.demo.redis.controller;

import com.demo.redis.anno.RequestRuntimeLog;
import com.demo.redis.service.UserService;
import com.demo.redis.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestRuntimeLog
    @PostMapping("/{id}")
    public Result get(@PathVariable Long id) {
        return userService.getUserById(id);
    }

}
