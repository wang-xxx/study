package com.demo.ssm.test;

import com.demo.ssm.pojo.Employee;
import com.demo.ssm.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@Slf4j
@SpringJUnitConfig(locations = {"classpath:spring-mapper.xml", "classpath:spring-service.xml"})
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void test() {
        List<Employee> employeeList = employeeService.showAll();
        for (Employee employee : employeeList) {
            log.info("{}", employee);
        }
    }

}
