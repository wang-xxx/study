package com.demo.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangxing
 * @date 2024-07-19 14:43
 */
@EnableScheduling
@SpringBootApplication
public class IOCApplication {

    public static void main(String[] args) {
        SpringApplication.run(IOCApplication.class, args);
    }

}
