package com.demo.request.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
@RequestMapping("oldApi")
public class OldApiController {

    @GetMapping("data")
    @ResponseBody
    public void data(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String age = request.getParameter("age");
        System.out.println("username = " + username + ", password = " + password + ", age = " + age);
        response.getWriter().println("heihei");
    }

}
