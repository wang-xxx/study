package com.demo.restful.service.impl;

import com.demo.restful.pojo.Schedule;
import com.demo.restful.service.ScheduleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static Map<Integer, Schedule> scheduleMap;

    private static int maxId = 5;

    static {
        scheduleMap = new HashMap<>();
        scheduleMap.put(1, new Schedule(1, "学习java", true));
        scheduleMap.put(2, new Schedule(2, "学习Html", false));
        scheduleMap.put(3, new Schedule(3, "学习Css", false));
        scheduleMap.put(4, new Schedule(4, "学习JavaScript", false));
        scheduleMap.put(5, new Schedule(5, "学习Spring", false));
    }


    @Override
    public List<Schedule> getAll() {
        return new ArrayList<>(scheduleMap.values());
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        maxId++;
        schedule.setId(maxId);
        scheduleMap.put(maxId, schedule);
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        scheduleMap.put(schedule.getId(), schedule);
    }

    @Override
    public void removeById(Integer id) {
        scheduleMap.remove(id);
    }
}
