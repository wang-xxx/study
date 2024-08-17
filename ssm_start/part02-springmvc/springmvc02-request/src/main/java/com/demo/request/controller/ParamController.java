package com.demo.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.User;

import java.util.List;

@Controller
@RequestMapping("param")
public class ParamController {

    @GetMapping("data")
    @ResponseBody
    public String data(String username,
                       String password,
                       Integer age) {
        System.out.println("username = " + username + ", password = " + password + ", age = " + age);
        return "data OK";
    }

    @GetMapping("reqData")
    @ResponseBody
    public String reqData(@RequestParam(value = "userName", required = false, defaultValue = "小可爱") String username,
                          String password,
                          @RequestParam(defaultValue = "1") Integer age) {
        System.out.println("username = " + username + ", password = " + password + ", age = " + age);
        return "reqData OK";
    }

    @GetMapping("reqList")
    @ResponseBody
    public String reqList(@RequestParam("hobbies") List<String> hobbies) {
        hobbies.forEach(System.out::println);
        return "reqList OK";
    }

    @GetMapping("reqPojo")
    @ResponseBody
    public String reqPojo(User user) {
        System.out.println(user);
        return "reqPojo OK";
    }

}
