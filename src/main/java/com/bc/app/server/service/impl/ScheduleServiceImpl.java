package com.bc.app.server.service.impl;

import com.bc.app.server.entity.Schedule;
import com.bc.app.server.mapper.ScheduleMapper;
import com.bc.app.server.service.ScheduleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 日程
 *
 * @author zhou
 */
@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    ScheduleMapper scheduleMapper;

    /**
     * 新增日程
     *
     * @param schedule 日程
     */
    public void addSchedule(Schedule schedule) {
        scheduleMapper.addSchedule(schedule);
    }

}
