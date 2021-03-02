package com.bc.app.server.mapper;


import com.bc.app.server.entity.StockApplication;

import java.util.List;
import java.util.Map;

/**
 * @author whl
 */
public interface StockApplicationMapper {

    /**
     * 出入库
     *
     * @param stockApplication
     */
    void insert(StockApplication stockApplication);

    /**
     * 获取出入库列表
     *
     * @param paramsMap
     * @return
     */
    List<StockApplication> getStockApplicationList(Map<String, Object> paramsMap);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    StockApplication findById(String id);

}
