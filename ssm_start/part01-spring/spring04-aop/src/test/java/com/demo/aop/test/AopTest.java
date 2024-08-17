package com.demo.aop.test;

import com.demo.aop.anno.Calculator;
import com.demo.aop.anno.SpringConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(SpringConfig.class)
public class AopTest {

    @Autowired
    private Calculator calculator;

    @Test
    public void test() {
        calculator.add(1, 2);
    }
}
