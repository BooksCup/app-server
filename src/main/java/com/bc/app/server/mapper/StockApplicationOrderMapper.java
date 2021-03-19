package com.bc.app.server.mapper;


import com.bc.app.server.entity.StockApplicationOrder;

import java.util.List;

/**
 * @author whl
 */
public interface StockApplicationOrderMapper {

    /**
     * 批量添加
     *
     * @param stockApplicationOrderList
     */
    void insertList(List<StockApplicationOrder> stockApplicationOrderList);

    /**
     * 出入库ID
     *
     * @param stockApplicationId
     * @return
     */
    List<StockApplicationOrder> findByStockApplicationId(String stockApplicationId);

    /**
     * 批量修改
     *
     * @param sOrderList
     */
    void updateList(List<StockApplicationOrder> sOrderList);

    /**
     * 根据条件批量删除StockApplicationOrder数据
     *
     * @param sOrder
     */
    void deleteBatchStockApplicationOrder(StockApplicationOrder sOrder);
}
