package com.demo.aop.anno;

import org.aspectj.lang.annotation.Pointcut;

public class CommonPointCut {

    @Pointcut("execution(public int com.demo.aop.anno.CalculatorImpl.add(int,int))")
    public void pointCut() {
    }

}
