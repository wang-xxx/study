package com.demo.start.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 引入yml配置属性
 * 1.@Value("${mail.user}") 全层级名称
 * 2.@ConfigurationProperties(prefix = "mail")设置前缀，属性名与配置文件保持一致
 */
@ConfigurationProperties(prefix = "mail")
@Component
@Data
public class MailProperties {

    private String user;
    private String code;
    private String host;
    private boolean auth;

    /*@Value("${mail.user}")
    private String user;
    @Value("${mail.code}")
    private String code;
    @Value("${mail.host}")
    private String host;
    @Value("${mail.auth}")
    private boolean auth;*/


}
