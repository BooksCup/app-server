package com.bc.app.server.service;

import com.bc.app.server.entity.Schedule;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 日程
 *
 * @author zhou
 */
public interface ScheduleService {

    /**
     * 新增日程
     *
     * @param schedule 日程
     */
    void addSchedule(Schedule schedule);

    /**
     * 查询日程分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 日程分页信息
     */
    PageInfo<Schedule> getSchedulePageInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    /**
     * 删除日程
     *
     * @param scheduleId 日程ID
     */
    void deleteSchedule(String scheduleId);

}
