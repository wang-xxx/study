package com.demo.aop.xml;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)
public class ValidateAspect {

    @Before("com.demo.aop.xml.CommonPointCut.pointCut()")
    public void beforeValidate() {
        System.out.println("数据校验前置通知执行");
    }

}
