package com.demo.tx.test;

import com.demo.tx.config.SpringConfig;
import com.demo.tx.service.StudentService;
import com.demo.tx.service.TopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.FileNotFoundException;

//@SpringJUnitConfig(locations = {"classpath:spring-xml.xml"})
@SpringJUnitConfig(classes = SpringConfig.class)
public class TxTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TopService topService;

    @Test
    public void test() throws FileNotFoundException {
        studentService.changeInfo();
    }

    @Test
    public void test2() {
        topService.topService();
    }

}
