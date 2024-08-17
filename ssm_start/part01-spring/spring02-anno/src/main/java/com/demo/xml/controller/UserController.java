package com.demo.xml.controller;

import com.demo.xml.pojo.User;
import com.demo.xml.service.UserService;

import java.util.List;

public class UserController {

    private UserService userService;

    public List<User> listAll() {
        System.out.println("控制层UserController.listAll()");
        return userService.showAll();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
