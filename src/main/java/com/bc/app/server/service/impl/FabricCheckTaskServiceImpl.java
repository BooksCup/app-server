package com.bc.app.server.service.impl;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.FabricCheckLotInfo;
import com.bc.app.server.entity.FabricCheckTask;
import com.bc.app.server.mapper.FabricCheckLotInfoMapper;
import com.bc.app.server.mapper.FabricCheckTaskMapper;
import com.bc.app.server.service.FabricCheckTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 面料盘点任务应用软件
 *
 * @author qiu
 */
@Service("fabricCheckTaskService")
public class FabricCheckTaskServiceImpl implements FabricCheckTaskService {

    @Autowired
    FabricCheckTaskMapper fabricCheckTaskMapper;

    @Autowired
    FabricCheckLotInfoMapper fabricCheckLotInfoMapper;

    /**
     * 添加面料盘点任务信息
     *
     * @param fabricCheckTask 面料盘点任务信息
     */
    @Override
    public void addFabricCheckTask(FabricCheckTask fabricCheckTask) {
        fabricCheckTaskMapper.addFabricCheckTask(fabricCheckTask);
    }

    /**
     * 获取面料盘点任务分页信息
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 面料盘点任务分页信息
     */
    @Override
    public PageInfo<FabricCheckTask> getFabricCheckTaskPageInfo(String enterpriseId, String keyword, Integer pageNum, Integer pageSize) {
        Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("keyword", keyword);
        PageHelper.startPage(pageNum, pageSize);
        List<FabricCheckTask> fabricCheckTaskList = fabricCheckTaskMapper.getFabricCheckTaskPageInfo(paramMap);
        if (CollectionUtils.isNotEmpty(fabricCheckTaskList)) {
            for (FabricCheckTask fabricCheckTask : fabricCheckTaskList) {
                Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
                map.put("checkTaskId", fabricCheckTask.getId());
                List<FabricCheckLotInfo> fabricCheckLotInfoList = fabricCheckLotInfoMapper.selectListByCheckTaskId(map);
                fabricCheckTask.setFabricCheckLotInfoList(fabricCheckLotInfoList);
            }
        }
        return new PageInfo<>(fabricCheckTaskList);
    }

    /**
     * 通过id更新数据
     *
     * @param fabricCheckTask 任务数据
     */
    @Override
    public void batchUpdateFabricCheckTaskById(FabricCheckTask fabricCheckTask) {
        fabricCheckTaskMapper.batchUpdateFabricCheckTaskById(fabricCheckTask);
    }

}
