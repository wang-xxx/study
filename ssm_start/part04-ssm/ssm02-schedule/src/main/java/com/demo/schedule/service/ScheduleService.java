package com.demo.schedule.service;

import com.demo.schedule.pojo.Schedule;
import com.demo.schedule.util.PageBean;

public interface ScheduleService {

    PageBean<Schedule> showAll(int currentPage, int pageSize);

}
