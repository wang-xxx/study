package com.demo.ioc.annotation;

import java.lang.annotation.*;

/**
 * @author wangxing
 * @date 2024-07-20 11:30
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TestLog {
}
