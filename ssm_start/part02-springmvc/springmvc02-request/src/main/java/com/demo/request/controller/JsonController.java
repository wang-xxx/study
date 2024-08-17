package com.demo.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Person;

@Controller
@RequestMapping("json")
public class JsonController {

    @PostMapping("/person")
    @ResponseBody
    public String person(@RequestBody Person person) {
        System.out.println(person);
        return "person Ok";
    }

}
