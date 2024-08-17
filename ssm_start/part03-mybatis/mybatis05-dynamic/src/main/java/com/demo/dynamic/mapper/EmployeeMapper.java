package com.demo.dynamic.mapper;


import com.demo.dynamic.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    List<Employee> selectByCondition(Map<String, Object> condition);

    void updateByCondition(Employee employee);

    List<Employee> selectByTrimCondition(Map<String, Object> condition);

    List<Employee> selectByChooseCondition(Map<String, Object> condition);

    List<Employee> selectByForeachIds(Integer... ids);

    void insertByForeach(List<Employee> employeeList);

    void updateByForeach(List<Employee> employeeList);

    List<Employee> selectAll();

}
