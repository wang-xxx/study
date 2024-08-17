package com.demo.other.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
public class LoginController {

    @GetMapping("admin")
    public String admin() {
        System.out.println("目标登录方法");
        return "admin ok";
    }

    @GetMapping("register")
    public String register() {
        System.out.println("目标注册方法");
        return "register ok";
    }

    @GetMapping("register/test")
    public String register2() {
        System.out.println("目标注册方法");
        return "register2 ok";
    }

    @GetMapping("exit")
    public String exit() {
        System.out.println("目标退出方法");
        return "exit ok";
    }

}
