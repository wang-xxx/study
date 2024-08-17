package com.demo.allcnno.controller;

import com.demo.allcnno.pojo.User;
import com.demo.allcnno.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Value("呵呵呵")
    private String name;
    private int age;

    public List<User> listAll() {
        System.out.println("控制层UserController.listAll()");
        return userService.showAll();
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
