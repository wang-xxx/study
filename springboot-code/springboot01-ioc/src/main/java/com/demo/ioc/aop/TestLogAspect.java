package com.demo.ioc.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

/**
 * @author wangxing
 * @date 2024-07-20 10:36
 */
@Slf4j
@Aspect
@Component
public class TestLogAspect {

    @Pointcut("@annotation(com.demo.ioc.annotation.TestLog)")
    private void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("Before");
    }

    @AfterReturning("pointcut()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("AfterReturning");
    }

    @AfterThrowing("pointcut()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("AfterThrowing");
    }

    @After("pointcut()")
    public void doAfter(JoinPoint joinPoint) {
        log.info("After");
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Instant start = Instant.now();
        // 获取类
        Class<?> clazz = joinPoint.getTarget().getClass();
        String clazzName = clazz.getName();
        // 获取方法名和参数
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        // 执行方法
        Object result = joinPoint.proceed();
        // 计算执行时间
        Instant end = Instant.now();
        long millis = Duration.between(start, end).toMillis();
        log.info("执行完成，类名：{}，方法名：{}，参数：{}，结果：{}，耗时：{}", clazzName, methodName, args, result, millis);
        return result;
    }

}