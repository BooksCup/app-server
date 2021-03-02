package com.bc.app.server.service;

import com.bc.app.server.entity.StockApplication;

import java.util.List;
import java.util.Map;

/**
 * @author whl
 */

public interface StockApplicationService {

    /**
     * 新增
     */
    void insert(StockApplication stockApplication);

    /**
     * 获取列表
     *
     * @return
     */
    List<StockApplication> getStockApplicationList(Map<String,Object> paramsMap);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    StockApplication findById(String id);
}
