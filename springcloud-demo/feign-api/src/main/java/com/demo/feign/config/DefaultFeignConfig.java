package com.demo.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author wangxing
 * @date 2024-07-17 07:57
 */
public class DefaultFeignConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

}
