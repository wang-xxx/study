package com.demo.schedule.service.impl;

import com.demo.schedule.mapper.ScheduleMapper;
import com.demo.schedule.pojo.Schedule;
import com.demo.schedule.service.ScheduleService;
import com.demo.schedule.util.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Override
    public PageBean<Schedule> showAll(int currentPage, int pageSize) {
        //分页
        PageHelper.startPage(currentPage, pageSize);
        //查询数据
        List<Schedule> scheduleList = scheduleMapper.selectAll();
        //封装pageInfo
        PageInfo<Schedule> pageInfo = new PageInfo<>(scheduleList);
        //处理数据，转为PageBean
        PageBean<Schedule> pageBean = new PageBean<>(
                currentPage, //当前页码
                pageInfo.getSize(), //当前页多少条数据
                pageInfo.getTotal(),//数据总条数
                pageInfo.getList());//当前页o数据
        return pageBean;
    }
}
