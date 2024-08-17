package com.demo.ioc.controller;

import com.demo.ioc.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * @author wangxing
 * @date 2024-07-21 11:57
 */
@Slf4j
@Controller
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/test")
    public ModelAndView getTest() {
        ModelAndView mv = new ModelAndView();
        // 视图名
        mv.setViewName("test");
        // 想传的数据
        mv.addObject("data1", "<h2>数据1</h2>");
        mv.addObject("users", Arrays.asList(1, 2, 3, 4, 5));
        return mv;
    }

    @GetMapping("/test/{id}")
    @ResponseBody
    public String getTest(@PathVariable Long id) {
        log.info("test请求：{}", id);
        return testService.testHotswap(id);
    }

}
