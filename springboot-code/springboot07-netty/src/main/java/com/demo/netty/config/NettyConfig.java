package com.demo.netty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "netty.server")
@Component
@Data
public class NettyConfig {

    private int port;
    private String address;

}
