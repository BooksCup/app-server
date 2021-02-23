package com.bc.app.server.mapper;


import com.bc.app.server.entity.FabricCheckLotInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 面料盘点-缸信息 应用
 *
 * @author qiu
 */
public interface FabricCheckLotInfoMapper {

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
     * 通过面料盘点任务表id查询信息
     *
     * @param map
     * @return
     */
    List<FabricCheckLotInfo> selectListByCheckTaskId(Map<String, String> map);

}
