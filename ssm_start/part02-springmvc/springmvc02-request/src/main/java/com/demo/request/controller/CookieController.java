package com.demo.request.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("cookie")
public class CookieController {

    @GetMapping("getCookie")
    @ResponseBody
    public String getCookie(@CookieValue("JSESSIONID") String sessionId) {
        System.out.println("sessionId = " + sessionId);
        return "get cookie ok";
    }

    @GetMapping("setCookie")
    @ResponseBody
    public String setCookie(HttpSession session) {
        System.out.println("session.getId()=" + session.getId());
        return "set cookie ok";
    }


}
