package com.demo.mp;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mp.entity.User;
import com.demo.mp.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PageQueryTest {

    @Autowired
    private UserService userService;

    @Test
    void testPageQuery() {
        //分页查询
        //分页条件
        int pageNo = 1, pageSize = 2;
        Page<User> pageInfo = Page.of(pageNo, pageSize);
        //排序条件
        pageInfo.addOrder(new OrderItem().setColumn("age").setAsc(false));
        pageInfo.addOrder(new OrderItem().setColumn("gender").setAsc(true));

        Page<User> pages = userService.page(pageInfo);
        System.out.println("total = " + pages.getTotal());
        System.out.println("pages = " + pages.getPages());
        pages.getRecords().forEach(System.out::println);
    }
}
