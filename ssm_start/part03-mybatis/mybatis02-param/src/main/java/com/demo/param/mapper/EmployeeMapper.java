package com.demo.param.mapper;


import com.demo.param.pojo.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeMapper {

    List<Employee> selectAll();

    Employee selectById(Integer empId);

    List<Employee> selectAllByRules(String rules);

    void deleteById(Integer empId);

    void insertEmployee(Employee employee);

    void updateEmployee(Integer empId, Double empSalary);

    /**
     * 传入多个零散数据，可以封装一个map:key是参数名，value是参数值
     *
     * @param condition
     * @return
     */
    List<Employee> selectByCondition(Map<String, Object> condition);
}
