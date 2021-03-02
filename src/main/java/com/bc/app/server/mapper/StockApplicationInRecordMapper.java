package com.bc.app.server.mapper;


import com.bc.app.server.entity.StockApplicationInRecord;

import java.util.List;

/**
 * @author whl
 */
public interface StockApplicationInRecordMapper {

    /**
     * 批量添加
     *
     * @param stockApplicationInRecordList
     */
    void insertList(List<StockApplicationInRecord> stockApplicationInRecordList);

    /**
     * 根据出入库查询
     *
     * @param stockApplicationId
     * @return
     */
    List<StockApplicationInRecord> findByStockApplicationId(String stockApplicationId);
}
