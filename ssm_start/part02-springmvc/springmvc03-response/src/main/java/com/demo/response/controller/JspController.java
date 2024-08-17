package com.demo.response.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("jsp")
public class JspController {

    /**
     * 当没有@ResponseBody时，返回的字符串给试图解析器
     * 物理试图 = 前缀 + 逻辑视图名 + 后缀
     * /WEB_INF/index.jsp
     *
     * @return
     */
    @GetMapping("index")
    public String index(Model model) {
        model.addAttribute("msg", "消息测试");
        return "index";//逻辑视图名
    }

    @GetMapping("jump")
    public String jump(Model model) {
        model.addAttribute("msg", "消息不能太长");
        //转发可以共享请求的数据
        //return "forward:/jsp/forward";
        //重定向无法共享请求的数据
        return "redirect:/jsp/forward";
    }

    @GetMapping("forward")
    public String forward() {
        System.out.println("嘿嘿嘿");
        return "forward";
    }

    @GetMapping("redirect")
    public String redirect() {
        System.out.println("哈哈哈");
        return "redirect";
    }


}

