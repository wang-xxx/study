package com.demo.aop.test;


import com.demo.aop.xml.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(locations = {"classpath:spring-xml.xml"})
public class XmlTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void test() {
        calculator.add(1, 2);
    }
}
