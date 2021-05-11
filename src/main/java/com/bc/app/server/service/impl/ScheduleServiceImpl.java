package com.bc.app.server.service.impl;

import com.bc.app.server.entity.Schedule;
import com.bc.app.server.mapper.ScheduleMapper;
import com.bc.app.server.service.ScheduleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    @Override
    public void addSchedule(Schedule schedule) {
        scheduleMapper.addSchedule(schedule);
    }

    /**
     * 查询日程分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 日程分页信息
     */
    @Override
    public PageInfo<Schedule> getSchedulePageInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Schedule> scheduleList = scheduleMapper.getScheduleList(paramMap);
        return new PageInfo<>(scheduleList);
    }

    /**
     * 删除日程
     *
     * @param scheduleId 日程ID
     */
    @Override
    public void deleteSchedule(String scheduleId) {
        scheduleMapper.deleteSchedule(scheduleId);
    }

}
