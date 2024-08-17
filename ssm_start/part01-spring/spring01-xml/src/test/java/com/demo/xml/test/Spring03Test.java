package com.demo.xml.test;

import com.demo.xml.pojo.HappyComponent;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Spring03Test {

    @Test
    public void test() {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring03.xml");
        HappyComponent happyComponent = ioc.getBean("happyComponent", HappyComponent.class);
        System.out.println(happyComponent.getHappyMachine().getMachineName());
    }

}
