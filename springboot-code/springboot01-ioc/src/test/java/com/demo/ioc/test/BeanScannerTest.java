package com.demo.ioc.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

/**
 * @author wangxing
 * @date 2024-07-19 15:16
 */
@SpringBootTest
@Slf4j
public class BeanScannerTest {

    public static void main(String[] args) {
        SimpleBeanDefinitionRegistry registry = new SimpleBeanDefinitionRegistry();

        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.scan("com.demo.ioc");
        log.info("{}", registry.getBeanDefinitionNames());
    }

}
