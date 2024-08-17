package com.demo.docker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangxing
 * @date 2024-07-21 11:57
 */
@Slf4j
@RestController
public class TestController {

    @GetMapping("/test/{id}")
    public String getTest(@PathVariable Long id) {
        log.info("test请求：{}", id);
        return "hello test";
    }

}
