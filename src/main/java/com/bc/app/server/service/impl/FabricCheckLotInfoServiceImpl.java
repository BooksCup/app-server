package com.bc.app.server.service.impl;


import com.bc.app.server.entity.FabricCheckLotInfo;
import com.bc.app.server.mapper.FabricCheckLotInfoMapper;
import com.bc.app.server.service.FabricCheckLotInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * 添加面料盘点-缸信息
     *
     * @param fabricCheckLotInfo 面料盘点-缸信息
     */
    @Override
    public void addFabricCheckLotInfo(FabricCheckLotInfo fabricCheckLotInfo) {
        fabricCheckLotInfoMapper.addFabricCheckLotInfo(fabricCheckLotInfo);
    }

    /**
     * 更新面料盘点-缸信息表信息
     *
     * @param map 入参
     */
    @Override
    public void updateById(Map<String, String> map) {
        fabricCheckLotInfoMapper.updateById(map);
    }

}
