package com.demo.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangxing
 * @date 2024-07-17 08:39
 */
@Component
@ConfigurationProperties(prefix = "jwt.auth")
@Data
public class AuthProperties {

    private List<String> excludePaths;
}
