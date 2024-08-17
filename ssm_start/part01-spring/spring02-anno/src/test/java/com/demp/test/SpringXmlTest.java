package com.demp.test;

import com.demo.xml.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlTest {

    @Test
    public void Test() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-xml.xml");

        /*UserDao userDao = ioc.getBean("userDao", UserDao.class);
        userDao.selectAll();*/

        /*UserService userService = ioc.getBean("userService", UserService.class);
        userService.showAll();*/

        UserController userController = ioc.getBean("userController", UserController.class);
        userController.listAll();
    }

}
