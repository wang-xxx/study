package com.demo.xml.test;

import com.demo.xml.pojo.HappyComponent;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring08Test {

    @Test
    public void test() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring08.xml");

        HappyComponent happyComponent = ioc.getBean("happyComponent", HappyComponent.class);
        HappyComponent happyComponent2 = ioc.getBean("happyComponent", HappyComponent.class);
        System.out.println(happyComponent == happyComponent2);
    }

}
