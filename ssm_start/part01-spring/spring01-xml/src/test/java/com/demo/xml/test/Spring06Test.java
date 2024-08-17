package com.demo.xml.test;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLException;

public class Spring06Test {

    @Test
    public void test() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");

        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println("connection：" + connection);
    }

    @Test
    public void test2() throws SQLException {
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring06.xml");
        DruidDataSource dataSource = ioc.getBean("dataSource", DruidDataSource.class);

        DruidPooledConnection connection = dataSource.getConnection();
        System.out.println("connection：" + connection);
    }
}
