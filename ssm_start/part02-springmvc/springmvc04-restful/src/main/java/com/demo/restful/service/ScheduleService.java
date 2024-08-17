package com.demo.restful.service;

import com.demo.restful.pojo.Schedule;

import java.util.List;

public interface ScheduleService {

    List<Schedule> getAll();

    void saveSchedule(Schedule schedule);

    void updateSchedule(Schedule schedule);

    void removeById(Integer id);

}
