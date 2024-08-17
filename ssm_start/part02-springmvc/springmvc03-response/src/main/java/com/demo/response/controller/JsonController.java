package com.demo.response.controller;

import com.demo.response.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("json")
public class JsonController {

    @PostMapping("getUser")
    @ResponseBody
    public User getUser(@RequestBody User user) {
        System.out.println("user = " + user);
        return user;
    }

}
