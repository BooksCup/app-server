package com.bc.app.server.mapper;

import com.bc.app.server.entity.FabricCheckTask;

import java.util.List;
import java.util.Map;

/**
 * 面料盘点任务应用
 *
 * @author qiu
 */
public interface FabricCheckTaskMapper {

    /**
     * 添加面料盘点任务信息
     *
     * @param fabricCheckTask 面料盘点任务信息
     */
    void addFabricCheckTask(FabricCheckTask fabricCheckTask);

    /**
     * 获取面料盘点任务分页信息
     *
     * @param paramMap 查询入参
     * @return 面料盘点任务分页信息
     */
    List<FabricCheckTask> getFabricCheckTaskPageInfo(Map<String, String> paramMap);

}