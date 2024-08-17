package com.demo.ssm.service.impl;

import com.demo.ssm.mapper.EmployeeMapper;
import com.demo.ssm.pojo.Employee;
import com.demo.ssm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> showAll() {
        return employeeMapper.selectAll();
    }
}
