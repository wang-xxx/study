package com.demo.aop.xml;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {

    @Pointcut("execution(public int com.demo.aop.xml.CalculatorImpl.add(int,int))")
    public void pointCut() {
    }

}
