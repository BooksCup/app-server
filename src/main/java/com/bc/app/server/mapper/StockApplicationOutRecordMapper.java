package com.bc.app.server.mapper;

import com.bc.app.server.entity.StockApplicationOutRecord;

import java.util.List;

/**
 * 出库记录
 *
 * @author zhou
 */
public interface StockApplicationOutRecordMapper {

    /**
     * 批量添加出库记录
     *
     * @param stockApplicationOutRecordList 出库记录列表
     */
    void insertList(List<StockApplicationOutRecord> stockApplicationOutRecordList);

}
