package com.demo.validate.controller;

import com.demo.validate.pojo.Product;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("product")
public class ProductController {

    @PostMapping("json")
    public String json(@RequestBody @Validated Product product) {
        System.out.println("product = " + product);
        return "json ok";
    }

}
