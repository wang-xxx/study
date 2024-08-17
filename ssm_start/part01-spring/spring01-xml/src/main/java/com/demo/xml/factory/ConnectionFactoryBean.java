package com.demo.xml.factory;

import org.springframework.beans.factory.FactoryBean;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactoryBean implements FactoryBean<Connection> {


    /**
     * 该工厂生产的对象
     *
     * @return
     * @throws Exception
     */
    @Override
    public Connection getObject() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
        return connection;
    }

    /**
     * 该工厂生产的对象类型
     *
     * @return
     * @throws Exception
     */
    @Override
    public Class<?> getObjectType() {
        return Connection.class;
    }

    /**
     * 该工程生产的对象是否为单例
     *
     * @return
     */
    @Override
    public boolean isSingleton() {
        return true;
    }
}
