package com.bc.app.server.mapper;

import com.bc.app.server.entity.Schedule;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取日程列表
     *
     * @param paramMap 参数map
     * @return 日程列表
     */
    List<Schedule> getScheduleList(Map<String, Object> paramMap);

    /**
     * 删除日程
     *
     * @param scheduleId 日程ID
     */
    void deleteSchedule(String scheduleId);

}
