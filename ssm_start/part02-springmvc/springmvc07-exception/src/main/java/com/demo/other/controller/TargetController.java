package com.demo.other.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("target")
public class TargetController {

    @GetMapping("test1")
    public String test1() {
        int i = 10 / 0;
        return "test1 ok";
    }

    @GetMapping("test2")
    public String test2() {
        String s = null;
        int i = s.length();
        return "test2 ok";
    }

    @GetMapping("test3")
    public String test3() {
        int i = Integer.valueOf("abc");
        return "test3 ok";
    }

    @GetMapping("test4")
    public String test4() throws ClassNotFoundException {
        Class.forName("cm.demo.other.controller.TestController");
        return "test4 ok";
    }

}
