package com.demp.test;

import com.demo.anno.config.ConfigProperties;
import com.demo.anno.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAnnoTest {

    @Test
    public void test() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-anno.xml");

        UserController userController = ioc.getBean("userController", UserController.class);
        System.out.println("userController：" + userController);
        userController.listAll();
        System.out.println(userController.getName());

        /*UserService userService = ioc.getBean("userServiceImpl", UserService.class);
        System.out.println("userService：" + userService);

        UserDao userDao = ioc.getBean("userDaoImpl", UserDao.class);
        System.out.println("userDao：" + userDao);*/

        ConfigProperties configProperties = ioc.getBean("configProperties", ConfigProperties.class);
        System.out.println("configProperties：" + configProperties.getName() + "\t" + configProperties.getAge());
    }

}
