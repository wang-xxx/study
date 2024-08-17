package com.demo.request.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("path")
public class PathController {

    @GetMapping("/{id}")
    @ResponseBody
    public String showUserById(@PathVariable("id") Integer id) {
        System.out.println("id = " + id);
        return "show Ok";
    }

    @GetMapping("/{id}/{pageIndex}/{pageSize}")
    @ResponseBody
    public String getUser(@PathVariable Integer id, @PathVariable Integer pageIndex, @PathVariable Integer pageSize) {
        System.out.println("id = " + id + ", pageIndex = " + pageIndex + ", pageSize = " + pageSize);
        return "show Ok";
    }

}
