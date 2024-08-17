package com.demo.result.mapper;


import com.demo.result.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    Integer selectCount();

    Employee selectByEmpId(Integer empId);

    List<Employee> selectByGender(String empGender);

    Map<String, Object> selectByMap();

    Integer insertEmployee(Employee employee);

}
