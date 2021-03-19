package com.bc.app.server.service;


import com.bc.app.server.entity.FabricCheckLotInfo;

import java.util.List;
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

    /**
     * 根据任务表id查询缸号集合
     *
     * @param map 入参
     * @return 缸号集合
     */
    List<FabricCheckLotInfo> getLotNoListByCheckTaskId(Map<String, String> map);
}
