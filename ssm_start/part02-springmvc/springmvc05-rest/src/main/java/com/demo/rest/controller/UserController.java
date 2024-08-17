package com.demo.rest.controller;

import com.demo.rest.pojo.User;
import org.springframework.web.bind.annotation.*;

/**
 * 数据结构：User(id 唯一标识, name  用户名, age 年龄)
 * 功能分析：
 * <p>
 * -用户数据分页展示功能(条件：page 页码默认1，size 每页显示条数默认10)
 * 传统方式：user/showAllByPage?page=1&size=10
 * REST：user/1/10
 * <p>
 * -保存用户功能
 * /user    post    传json数据
 * <p>
 * -根据用户id查询用户信息
 * /user/1001   get
 * <p>
 * -根据用户id更新用户信息
 * /user    put 传json数据
 * <p>
 * -根据用户id删除用户信息
 * /user/1001   delete
 * <p>
 * --多条件模糊查询用户功能（例如：keyword模糊关键字，page页数，size每页数量）
 * /user?keyword=x&page=x&size=x
 */
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping
    public String getAll(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        System.out.println("page = " + page + ", size = " + size);
        return "page ok";
    }

    @GetMapping("like")
    public String showByLike(@RequestParam(name = "keyword", required = false, defaultValue = "*") String keyword,
                             @RequestParam(name = "page", required = false, defaultValue = "1") Integer page,
                             @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {
        System.out.println("keyword = " + keyword + ", page = " + page + ", size = " + size);
        return "keyword ok";
    }

    @PostMapping
    public String save(@RequestBody User user) {
        System.out.println("user = " + user);
        return "save ok";
    }

    @GetMapping("{id}")
    public String showById(@PathVariable Integer id) {
        System.out.println("id = " + id);
        return "id ok";
    }

    @PutMapping
    public String modify(@RequestBody User user) {
        System.out.println("user = " + user);
        return "modify ok";
    }

    @DeleteMapping("{id}")
    public String remove(@PathVariable Integer id) {
        System.out.println("id = " + id);
        return "delete ok";
    }

}
