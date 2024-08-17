package com.demo.xml.test;

import com.demo.xml.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring09Test {

    @Test
    public void Test() {
        ConfigurableApplicationContext ioc = new ClassPathXmlApplicationContext("spring09.xml");
        User user = ioc.getBean("user", User.class);
        System.out.println("user=" + user);
        ioc.close();
    }

}
