package com.demo.xml.test;

import com.demo.xml.pojo.HappyMachine;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;

public class Spring07Test {

    @Test
    public void test() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring07.xml");
        Connection connection = ioc.getBean("connection", Connection.class);
        System.out.println(connection);

        HappyMachine happyMachine = ioc.getBean("happyMachine", HappyMachine.class);
        System.out.println(happyMachine.getMachineName());
    }

}
