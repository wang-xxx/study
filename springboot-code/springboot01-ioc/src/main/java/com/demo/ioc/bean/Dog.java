package com.demo.ioc.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author wangxing
 * @date 2024-07-19 14:55
 */
@Data
@Component
public class Dog {

    private String color;
    private Integer age;

}
