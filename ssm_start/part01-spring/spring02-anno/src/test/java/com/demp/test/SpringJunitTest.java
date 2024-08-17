package com.demp.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.demo.allcnno.config.SpringConfig;
import com.demo.allcnno.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(value = {SpringConfig.class})
public class SpringJunitTest {

    @Autowired
    private UserController userController;

    @Autowired
    private DruidDataSource dataSource;

    @Test
    public void test() throws Exception {
        userController.listAll();
        System.out.println(dataSource.getConnection());
    }

}
