package com.demo.dynamic.test;

import com.demo.dynamic.mapper.EmployeeMapper;
import com.demo.dynamic.pojo.Employee;
import com.demo.dynamic.util.SqlSessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class EmployeeTest {

    @Test
    public void test() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Employee> employeeList = new ArrayList<>();

        //动态查询条件
        Map<String, Object> condition = new HashMap<>();
        condition.put("empId", 1);
        condition.put("empName", "张三");
        condition.put("empSalary", 3000.11);
        condition.put("empGender", "男");

        //employeeList = mapper.selectByCondition(condition);


        /*Employee employee = new Employee(2, null, null, null);
        mapper.updateByCondition(employee);*/


        //employeeList = mapper.selectByTrimCondition(condition);


        //employeeList = mapper.selectByChooseCondition(condition);


        //查询id为1.2.4的
        /*Integer[] ids = {1, 2, 4};
        employeeList = mapper.selectByForeachIds(ids);*/

        //批量插入
        /*employeeList = new ArrayList<>();
        employeeList.add(new Employee(null, "tom", 3000.11, "男"));
        employeeList.add(new Employee(null, "jerry", 2400.44, "男"));
        employeeList.add(new Employee(null, "oldGodMom", 2200.12, "女"));
        mapper.insertByForeach(employeeList);*/

        /*employeeList = new ArrayList<>();
        employeeList.add(new Employee(6, "tom", 2888.88, "男"));
        employeeList.add(new Employee(7, "jerry", 2666.66, "男"));
        employeeList.add(new Employee(8, "oldGodMom", 2444.44, "女"));
        mapper.updateByForeach(employeeList);*/

        for (Employee employee : employeeList) {
            log.info("{}", employee);
        }

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testPage() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);

        //分页插件本质是个拦截器，执行查询之前先查询总条数、总页数
        PageHelper.startPage(1, 3);
        List<Employee> employeeList = mapper.selectAll();

        PageInfo<Employee> pageInfo = new PageInfo<>(employeeList);
        System.out.println("当前页：" + pageInfo.getPageNum());
        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("是否为第一页：" + pageInfo.isIsFirstPage());
        System.out.println("是否为最后一页：" + pageInfo.isIsLastPage());

        for (Employee employee : employeeList) {
            log.info("{}", employee);
        }

        sqlSession.commit();
        sqlSession.close();
    }

}
