package com.demo.aop.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect//切面类：存放额外功能代码
@Component//注入到Spring容器
@Order(2)
public class LogAspect {

    /**
     * 方法执行结束前，执行该通知
     *
     * @param joinPoint 切入的方法
     */
    @Before("com.demo.aop.anno.CommonPointCut.pointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("日志通知：前置通知，" + name + "方法执行，入参：" + Arrays.toString(args));
    }

    /**
     * 方法执行结束后，正确返回结果，执行该通知
     *
     * @param joinPoint 切入的方法
     * @param result    返回的结果
     */
    //@AfterReturning(value = "com.demo.aop.anno.CommonPointCut.pointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("日志通知：返回通知执行，" + name + "方法执行结束，出参：" + result);
    }

    /**
     * 方法执行结束后，方法异常，执行该通知
     *
     * @param joinPoint 切入的方法
     * @param ex        异常信息
     */
    //@AfterThrowing(value = "com.demo.aop.anno.CommonPointCut.pointCut()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String name = joinPoint.getSignature().getName();
        System.out.println("日志通知：返回异常通知执行，" + name + "方法出现异常：" + ex.getMessage());
    }

    /**
     * 方法执行结束后，无论方法是否异常，都执行该通知
     */
    //@After("com.demo.aop.anno.CommonPointCut.pointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("日志通知：后置通知执行，" + name + "方法执行完毕");
    }

    @Around(value = "com.demo.aop.anno.CommonPointCut.pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        String name = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();
        Object result = null;
        //执行目标方法
        try {
            System.out.println("日志通知：环绕通知执行，" + name + "方法执行前，入参：" + Arrays.toString(args));
            result = proceedingJoinPoint.proceed();
            System.out.println("日志通知：环绕通知执行，" + name + "方法执行结束，出参：" + result);
        } catch (Throwable e) {
            System.out.println("日志通知：环绕通知执行，" + name + "方法出现异常：" + e.getMessage());
        } finally {
            System.out.println("日志通知：环绕通知执行，" + name + "方法执行完毕");
        }
        return result;
    }

}
