package com.demo.param.test;

import com.demo.param.mapper.EmployeeMapper;
import com.demo.param.pojo.Employee;
import com.demo.param.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class ParamTest {

    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employeeList = new ArrayList<>();

        // 查询所有
        /*employeeList = employeeMapper.selectAll();
        for (Employee employee : employeeList) {
            log.info("{}", employee);
        }*/

        // 查询单个
        //log.info("{}", employeeMapper.selectById(1));


        // 查询多个（动态）
        /*employeeList = employeeMapper.selectAllByRules("desc");
        for (Employee employee : employeeList) {
            log.info("{}", employee);
        }*/

        //删除
        //employeeMapper.deleteById(3);

        //插入实体
        //employeeMapper.insertEmployee(new Employee(null, "zhangsan", 2500.12, "女"));

        //更新多个参数
        //employeeMapper.updateEmployee(4, 3500.66);

        //传入多个参数，封装map
        HashMap<String, Object> condition = new HashMap<>();
        condition.put("empName", "张三");
        condition.put("empGender", "女");
        employeeList = employeeMapper.selectByCondition(condition);
        for (Employee employee : employeeList) {
            log.info("{}", employee);
        }

        sqlSession.commit();
        sqlSession.close();
    }
}
