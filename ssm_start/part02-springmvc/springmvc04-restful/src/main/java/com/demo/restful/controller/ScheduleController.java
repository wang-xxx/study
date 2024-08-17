package com.demo.restful.controller;

import com.demo.restful.pojo.Schedule;
import com.demo.restful.service.ScheduleService;
import com.demo.restful.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("listAll")
    public Result listAll() {
        List<Schedule> list = scheduleService.getAll();
        return Result.ok(list);
    }

    @PostMapping("add")
    public Result add(@RequestBody Schedule schedule) {
        scheduleService.saveSchedule(schedule);
        return Result.ok(null);
    }

    @PostMapping("modify")
    public Result modify(@RequestBody Schedule schedule) {
        scheduleService.updateSchedule(schedule);
        return Result.ok(null);
    }

    @GetMapping("remove/{id}")
    public Result modify(@PathVariable Integer id) {
        scheduleService.removeById(id);
        return Result.ok(null);
    }

}
