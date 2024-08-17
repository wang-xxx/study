package com.demo.start.controller;

import com.demo.start.pojo.MailProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class HelloController {

    @Resource
    private MailProperties mailProperties;

    @RequestMapping("hello")
    public String hello() {
        return "hello springboot";
    }

    @RequestMapping("mail")
    public String mail() {
        return mailProperties.toString();
    }

}
