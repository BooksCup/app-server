package com.bc.app.server.service;


import com.bc.app.server.entity.FabricCheckLotInfo;

import java.util.Map;

/**
 * 面料盘点-缸信息 应用程序
 *
 * @author qiu
 */
public interface FabricCheckLotInfoService {


    /**
     * 添加面料盘点-缸信息
     *
     * @param fabricCheckLotInfo 面料盘点-缸信息
     */
    void addFabricCheckLotInfo(FabricCheckLotInfo fabricCheckLotInfo);

    /**
     * 更新面料盘点-缸信息表信息
     *
     * @param map 入参
     */
    void updateById(Map<String, String> map);

}
