package com.demo.ssm.controller;

import com.demo.ssm.pojo.Employee;
import com.demo.ssm.service.EmployeeService;
import com.demo.ssm.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping
    @ResponseBody
    public Result showAll() {
        List<Employee> employeeList = employeeService.showAll();
        return Result.ok(employeeList);
    }

}
