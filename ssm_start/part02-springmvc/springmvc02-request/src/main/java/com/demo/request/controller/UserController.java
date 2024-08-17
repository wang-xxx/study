package com.demo.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public String register() {
        return "register OK";
    }

    @RequestMapping(value = "register2", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String register2(String userName) {
        return "exit OK";
    }

    @RequestMapping(value = "*/*", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String exit() {
        return "exit OK";
    }

}
