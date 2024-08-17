package com.demo.aop.xml;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.demo.aop")
@EnableAspectJAutoProxy
public class SpringConfig {
}
