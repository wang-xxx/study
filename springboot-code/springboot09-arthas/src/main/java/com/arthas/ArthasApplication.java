package com.arthas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author wangxing
 * @date 2024-07-22 13:20
 */
@EnableScheduling
@SpringBootApplication
public class ArthasApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArthasApplication.class, args);
    }

}
