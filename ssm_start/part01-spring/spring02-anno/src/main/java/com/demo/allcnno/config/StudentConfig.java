package com.demo.allcnno.config;

import com.demo.allcnno.pojo.Student;
import com.demo.allcnno.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

//@Configuration
public class StudentConfig {

    @Bean
    public Student student() {
        return new Student("张三", "男");
    }

    @Bean
    @Conditional({WindowsCondition.class})
    public User user1() {
        return new User("Windows", "123456");
    }

    @Bean
    @Conditional({LinuxCondition.class})
    public User user2() {
        return new User("Linux", "admin123");
    }

}
