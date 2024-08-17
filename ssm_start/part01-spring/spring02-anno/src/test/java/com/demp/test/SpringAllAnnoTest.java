package com.demp.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.demo.allcnno.config.SpringConfig;
import com.demo.allcnno.controller.UserController;
import com.demo.allcnno.pojo.HappyComponent;
import com.demo.allcnno.pojo.Student;
import com.demo.allcnno.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Map;

public class SpringAllAnnoTest {

    @Test
    public void Test() throws SQLException {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        UserController userController = ioc.getBean("userController", UserController.class);
        userController.listAll();

        DruidDataSource dataSource = ioc.getBean("db1", DruidDataSource.class);
        System.out.println(dataSource.getConnection());
        DruidDataSource dataSource2 = ioc.getBean("db2", DruidDataSource.class);
        System.out.println(dataSource2.getConnection());

        User user = ioc.getBean("user", User.class);
        System.out.println(user);

        HappyComponent happyComponent = ioc.getBean("happyComponent", HappyComponent.class);
        System.out.println("机器名称：" + happyComponent.getHappyMachine().getMachineName());

        Student student = ioc.getBean("student", Student.class);
        System.out.println(student);

        ioc.close();
    }

    @Test
    public void Test2() {
        AnnotationConfigApplicationContext ioc = new AnnotationConfigApplicationContext(SpringConfig.class);
        Map<String, User> users = ioc.getBeansOfType(User.class);
        System.out.println(users);
    }

}
