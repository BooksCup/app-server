package com.bc.app.server.service;

import com.bc.app.server.entity.FabricCheckTask;
import com.github.pagehelper.PageInfo;

/**
 * 面料盘点任务应用软件
 *
 * @author qiu
 */
public interface FabricCheckTaskService {

    /**
     * 添加面料盘点任务信息
     *
     * @param fabricCheckTask 面料盘点任务信息
     */
    void addFabricCheckTask(FabricCheckTask fabricCheckTask);

    /**
     * 获取面料盘点任务分页信息
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 面料盘点任务分页信息
     */
    PageInfo<FabricCheckTask> getFabricCheckTaskPageInfo(String enterpriseId, String keyword, Integer pageNum, Integer pageSize);

    /**
     * 通过id来更新数据
     *
     * @param fabricCheckTask 任务数据
     */
    void batchUpdateFabricCheckTaskById(FabricCheckTask fabricCheckTask);
}
