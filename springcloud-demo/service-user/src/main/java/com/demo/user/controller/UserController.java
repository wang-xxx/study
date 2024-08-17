package com.demo.user.controller;

import com.demo.feign.dto.User;
import com.demo.user.config.PatternProperty;
import com.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@RefreshScope
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //@Value("${pattern.dateformat}")
    //private String dateformat;

    @Autowired
    private PatternProperty patternProperty;

    @GetMapping("/now")
    public String now() {
        String dateformat = patternProperty.getDateformat();
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
    }

    @GetMapping("/property")
    public PatternProperty property() {
        return patternProperty;
    }


    @GetMapping("/{userId}")
    public User queryOrder(@PathVariable("userId") Long userId,
                           @RequestHeader(value = "addHeader", required = false) String addHeader) {
        System.out.println("headParam: " + addHeader);
        return userService.getById(userId);
    }

}
