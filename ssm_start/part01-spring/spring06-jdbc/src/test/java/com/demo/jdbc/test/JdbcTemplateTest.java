package com.demo.jdbc.test;

import com.demo.jdbc.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = {"classpath:spring-xml.xml"})
public class JdbcTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void Test() {
        //增删改
        /*String sql = "insert students(name,gender,age,class) values(?,?,?,?)";
        Object[] objects = {"zhangsan", "女", "16", "高中一班"};*/

        /*String sql = "update students set age = ? where id = ?";
        Object[] objects = {17, 6};*/

        String sql = "delete from students where id = ?";
        Object[] objects = {6};
        int update = jdbcTemplate.update(sql, objects);
        System.out.println(update);


    }

    @Test
    public void Test2() {
        //查
        //查单行单列
        /*String sql = "select count(*) from students";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);*/

        //查单行多列
        /*String sql = "select id,name,gender,age,classes from students where id = ?";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        Student student = jdbcTemplate.queryForObject(sql, rowMapper, 1);
        System.out.println(student);*/

        //查多行多列
        String sql = "select * from students";
        RowMapper<Student> rowMapper = new BeanPropertyRowMapper<>(Student.class);
        List<Student> students = jdbcTemplate.query(sql, rowMapper);
        students.forEach(System.out::println);
    }

}
