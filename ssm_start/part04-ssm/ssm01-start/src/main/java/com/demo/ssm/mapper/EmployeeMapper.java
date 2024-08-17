package com.demo.ssm.mapper;

import com.demo.ssm.pojo.Employee;

import java.util.List;

public interface EmployeeMapper {

    List<Employee> selectAll();

}
