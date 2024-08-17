package org.demo.auto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AutoConfigApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AutoConfigApplication.class, args);
        Object dispatcherServlet = context.getBean("dispatcherServlet");
        System.out.println(dispatcherServlet);
    }

}
