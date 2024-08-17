package com.demo.quick.test;

import com.demo.quick.mapper.EmployeeMapper;
import com.demo.quick.pojo.Employee;
import com.demo.quick.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class EmployeeTest {

    @Test
    public void test() throws IOException {
        //1.读取Mybatis配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        //2.基于配置文件，建立SqlSessionFactory工厂
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //3.基于SqlSessionFactory工厂，生成SqlSession(连接)对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        //4.根据接口的类对象，获取接口的实现类对象(动态代理)
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        //5.接口角度调用方法
        List<Employee> employeeList = employeeMapper.selectAll();
        //6.处理结果
        employeeList.forEach(System.out::println);
        //7.释放资源o
        sqlSession.close();
    }

    @Test
    public void test2() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        //4.根据接口的类对象，获取接口的实现类对象(动态代理)
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        //5.接口角度调用方法
        List<Employee> employeeList = employeeMapper.selectAll();
        //6.处理结果
        for (Employee employee : employeeList) {
            log.info("{}", employee);
        }
        //7.释放资源o
        sqlSession.close();
    }

}
