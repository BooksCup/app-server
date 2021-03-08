package com.bc.app.server.service.impl;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.FabricCheckLotInfo;
import com.bc.app.server.entity.FabricCheckRecord;
import com.bc.app.server.entity.FabricCheckTask;
import com.bc.app.server.mapper.FabricCheckLotInfoMapper;
import com.bc.app.server.mapper.FabricCheckRecordMapper;
import com.bc.app.server.mapper.FabricCheckTaskMapper;
import com.bc.app.server.service.FabricCheckTaskService;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
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

    @Autowired
    FabricCheckRecordMapper fabricCheckRecordMapper;

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
    public PageInfo<FabricCheckTask> getFabricCheckTaskPageInfo(String enterpriseId, String keyword, Integer pageNum,
                                                                Integer pageSize, String modifyTimeApply,String modifyTimeExamine) {
        Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("keyword", keyword);
        paramMap.put("modifyTimeApply", modifyTimeApply);
        paramMap.put("modifyTimeExamine", modifyTimeExamine);
        PageHelper.startPage(pageNum, pageSize);
        List<FabricCheckTask> fabricCheckTaskList = fabricCheckTaskMapper.getFabricCheckTaskPageInfo(paramMap);
        if (CollectionUtils.isNotEmpty(fabricCheckTaskList)) {
            for (FabricCheckTask fabricCheckTask : fabricCheckTaskList) {
                Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
                map.put("checkTaskId", fabricCheckTask.getId());
                List<FabricCheckLotInfo> fabricCheckLotInfoList = fabricCheckLotInfoMapper.selectListByCheckTaskId(map);
                if (CollectionUtils.isNotEmpty(fabricCheckLotInfoList)) {
                    for (FabricCheckLotInfo fabricCheckLotInfo : fabricCheckLotInfoList) {
                        FabricCheckRecord fabricCheckRecord = new FabricCheckRecord();
                        fabricCheckRecord.setCheckLotInfoId(fabricCheckLotInfo.getId());
                        GetByWarehouseIdVo countData = fabricCheckRecordMapper.getCountData(fabricCheckRecord);
                        fabricCheckLotInfo.setGetByWarehouseIdVo(countData);
                    }
                }
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

    /**
     * 通过id更新数据
     *
     * @param map 入参信息
     */
    @Override
    public void updateById(Map<String, String> map) {
        fabricCheckTaskMapper.updateById(map);
    }

}
