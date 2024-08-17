package com.demo.other.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/private/resource")
public class PrivateController {

    @RequestMapping("one")
    public Object one() {
        return "private one";
    }

    @RequestMapping("two")
    public Object two() {
        return "private two";
    }

    @RequestMapping("three")
    public Object three() {
        return "private three";
    }

    @RequestMapping("four")
    public Object four() {
        return "private four";
    }

}
