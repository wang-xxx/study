package com.demo.schedule.controller;

import com.demo.schedule.pojo.Schedule;
import com.demo.schedule.service.ScheduleService;
import com.demo.schedule.util.PageBean;
import com.demo.schedule.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/{currentPage}/{pageSize}")
    @ResponseBody
    public Result showByPage(@PathVariable("currentPage") Integer currentPage,
                             @PathVariable("pageSize") Integer pageSize) {
        PageBean<Schedule> pageBean = scheduleService.showAll(currentPage, pageSize);
        return Result.ok(pageBean);
    }

}
