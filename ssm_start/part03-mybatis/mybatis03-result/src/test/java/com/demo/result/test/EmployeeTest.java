package com.demo.result.test;

import com.demo.result.mapper.EmployeeMapper;
import com.demo.result.pojo.Employee;
import com.demo.result.util.SqlSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

@Slf4j
public class EmployeeTest {

    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        /*Integer count = mapper.selectCount();
        log.info("{}", count);

        Employee employee = mapper.selectByEmpId(1);
        log.info("{}", employee);

        List<Employee> employeeList = mapper.selectByGender("男");
        for (Employee employee1 : employeeList) {
            log.info("{}", employee1);
        }*/

        /*Map<String, Object> map = mapper.selectByMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            log.info("{}:{}", entry.getKey(), entry.getValue());
        }*/

        Employee insertEmp = new Employee(null, "王五", 2888.88, "女");
        Integer empId = mapper.insertEmployee(insertEmp);
        log.info("{}", empId);
        log.info("{}", insertEmp);

        sqlSession.commit();
        sqlSession.close();
    }

}
