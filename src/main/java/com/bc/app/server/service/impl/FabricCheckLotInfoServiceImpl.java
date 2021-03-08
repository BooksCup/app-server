package com.bc.app.server.service.impl;


import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.FabricCheckLotInfo;
import com.bc.app.server.entity.FabricCheckTask;
import com.bc.app.server.mapper.FabricCheckLotInfoMapper;
import com.bc.app.server.mapper.FabricCheckTaskMapper;
import com.bc.app.server.service.FabricCheckLotInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 面料盘点-缸信息 应用程序
 *
 * @author qiu
 */
@Service("fabricCheckLotInfoService")
public class FabricCheckLotInfoServiceImpl implements FabricCheckLotInfoService {

    @Autowired
    FabricCheckLotInfoMapper fabricCheckLotInfoMapper;

    @Autowired
    FabricCheckTaskMapper fabricCheckTaskMapper;
    /**
     * 添加面料盘点-缸信息
     *
     * @param fabricCheckLotInfo 面料盘点-缸信息
     */
    @Override
    public void addFabricCheckLotInfo(FabricCheckLotInfo fabricCheckLotInfo) {
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("id",fabricCheckLotInfo.getId());
        map.put("modifyTimeApply", "modifyTimeApply");
        fabricCheckTaskMapper.updateById(map);
        fabricCheckLotInfoMapper.addFabricCheckLotInfo(fabricCheckLotInfo);
    }

    /**
     * 更新面料盘点-缸信息表信息
     *
     * @param map 入参
     */
    @Override
    public void updateById(Map<String, String> map) {
        String fabricCheckTaskId = map.get("fabricCheckTaskId");
        if (!"".equals(fabricCheckTaskId) && null != fabricCheckTaskId){
            FabricCheckTask fabricCheckTask = new FabricCheckTask();
            fabricCheckTask.setId(fabricCheckTaskId);
            fabricCheckTaskMapper.batchUpdateFabricCheckTaskById(fabricCheckTask);
        }
        fabricCheckLotInfoMapper.updateById(map);
    }

}
