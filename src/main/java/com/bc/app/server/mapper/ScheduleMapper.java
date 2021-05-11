package com.bc.app.server.mapper;

import com.bc.app.server.entity.Schedule;

/**
 * 日程
 *
 * @author zhou
 */
public interface ScheduleMapper {

    /**
     * 新增日程
     *
     * @param schedule 日程
     */
    void addSchedule(Schedule schedule);

}
