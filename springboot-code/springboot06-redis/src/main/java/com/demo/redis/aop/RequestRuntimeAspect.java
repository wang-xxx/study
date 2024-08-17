package com.demo.redis.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class RequestRuntimeAspect {

    @Value("${logging.request-log-timeout}")
    private long requestTimeoutLog;

    //@Pointcut("@annotation(com.demo.redis.anno.RequestRuntimeLog)")
    @Pointcut("execution(* com.demo.redis.controller.*.*(..))")
    private void requestRuntimeLog() {
    }

    /*@Before("requestRuntimeLog()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println("日志通知：前置通知，" + name + "方法执行，入参：" + Arrays.toString(args));
    }

    @AfterReturning(value = "requestRuntimeLog()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        String name = joinPoint.getSignature().getName();
        System.out.println("日志通知：返回通知执行，" + name + "方法执行结束，出参：" + result);
    }

    @AfterThrowing(value = "requestRuntimeLog()", throwing = "ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex) {
        String name = joinPoint.getSignature().getName();
        System.out.println("日志通知：返回异常通知执行，" + name + "方法出现异常：" + ex.getMessage());
    }

    @After("requestRuntimeLog()")
    public void afterAdvice(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println("日志通知：后置通知执行，" + name + "方法执行完毕");
    }*/

    @Around(value = "requestRuntimeLog()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        LocalDateTime beginTime = LocalDateTime.now();
        //String name = proceedingJoinPoint.getSignature().getName();
        String name = proceedingJoinPoint.getSignature().toString();
        Object[] args = proceedingJoinPoint.getArgs();
        Object result = null;
        //执行目标方法
        try {
            log.info("日志通知：方法{}执行前，入参：{}", name, Arrays.toString(args));
            result = proceedingJoinPoint.proceed();
            log.info("日志通知：环绕通知执行，方法{}执行结束，出参：{}", name, result);
        } catch (Throwable e) {
            log.error("日志通知：方法{}出现异常：{}", name, e);
        } finally {
            LocalDateTime endTime = LocalDateTime.now();
            Duration duration = Duration.between(beginTime, endTime);
            long millis = duration.toMillis();
            String logStr = "日志通知：方法{}执行超时，耗时：{}ms";
            if (requestTimeoutLog > 0 && millis > requestTimeoutLog) {
                log.warn(logStr, name, millis);
            } else {
                log.info(logStr, name, millis);
            }
        }
        return result;
    }

}
