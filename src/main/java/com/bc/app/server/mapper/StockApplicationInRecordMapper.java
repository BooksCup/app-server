package com.bc.app.server.mapper;


import com.bc.app.server.entity.StockApplicationInRecord;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据goodsSpecId、stockApplicationId更新数据
     *
     * @param recordList 入参
     */
    void updateList(List<StockApplicationInRecord> recordList);

    /**
     * 根据条件批量删除StockApplicationInRecord数据
     *
     * @param r
     */
    void deleteBatchStockApplicationInRecord(StockApplicationInRecord r);

    /**
     * 仓库详情中的出入列表
     *
     * @return
     */
    List<Map<String, String>> getStockInfoByWareHouseId(String wareHouseId);

    /**
     * 通过仓库id查询仓库数据
     *
     * @param wareHouseId
     * @return
     */
    List<StockApplicationInRecord> getStockListByWareHouseId(String wareHouseId);
}
