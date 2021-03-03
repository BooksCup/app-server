package com.bc.app.server.service;

import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.entity.vo.StockApplicationVo;

import java.util.List;
import java.util.Map;

/**
 * @author whl
 */

public interface StockApplicationService {

    /**
     * 新增
     */
    void insert(StockApplication stockApplication, String specNums);

    /**
     * 获取列表
     *
     * @return
     */
    List<StockApplication> getStockApplicationList(Map<String, Object> paramsMap);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    StockApplicationVo findById(String id);
}
