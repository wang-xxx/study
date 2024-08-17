package com.demo.interview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 启动流程：
 *
 * 前置知识：
 *  1.ApplicationContextInitializer
 *  2.ApplicationListener
 *  3.BeanFactory
 *  4.BeanDefinition
 *  5.BeanFactoryPostProcessor
 *  6.Aware
 *  7.InitializingBean, DisposableBean
 *  8.BeanPostProcessor
 */
@SpringBootApplication
public class InterviewApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(InterviewApplication.class, args);

        /*ConfigurableEnvironment environment = applicationContext.getEnvironment();
        System.out.println(environment.getProperty("applicationName"));*/

        /*InterviewApplication bean = applicationContext.getBean(InterviewApplication.class);
        System.out.println(bean);*/

        //获取BeanDefinition
        /*ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
        BeanDefinition userBeanDefinition = beanFactory.getBeanDefinition("user");
        BeanDefinition abcBeanDefinition = beanFactory.getBeanDefinition("abc");
        System.out.println(userBeanDefinition.getClass());
        System.out.println(abcBeanDefinition.getClass());*/

        /*System.out.println(applicationContext.getBean("teacher"));*/
    }

}
