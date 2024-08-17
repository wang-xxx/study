package com.demo.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("header")
public class HeaderController {

    @GetMapping("accept")
    @ResponseBody
    public String accept(@RequestHeader("Accept") String accept) {
        System.out.println("accept = " + accept);
        if (accept.equals("IOS")) {
            System.out.println("---IOC---");
        }
        return "accept ok";
    }

}
