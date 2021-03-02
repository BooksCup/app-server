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
}
