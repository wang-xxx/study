package com.demo.other.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/resource")
public class PublicController {

    @RequestMapping("login")
    public Object login(HttpSession session) {
        session.setAttribute("user", "admin");
        return "login success";
    }

    @RequestMapping("one")
    public Object one() {
        return "public one";
    }

    @RequestMapping("two")
    public Object two() {
        return "public two";
    }

}
