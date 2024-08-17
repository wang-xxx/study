package com.demo.xml.test;

import com.demo.xml.pojo.HappyComponent;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring05Test {

    @Test
    public void test() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring05.xml");
        HappyComponent happyComponent = ioc.getBean("happyComponent", HappyComponent.class);
        System.out.println(happyComponent);
    }

}
