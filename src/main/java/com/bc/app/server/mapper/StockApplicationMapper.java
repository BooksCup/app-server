package com.bc.app.server.mapper;


import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.entity.StockApplicationPackage;
import com.bc.app.server.entity.StockApplicationPackageCategory;
import com.bc.app.server.entity.StockApplicationPackageRecord;
import com.bc.app.server.entity.vo.StockApplicationVo;

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
    StockApplicationVo findById(String id);

    /**
     * 获取订单价格信息
     *
     * @param orderId
     * @return
     */
    Map getOrderExtInfo(String orderId);


    /**
     * 装箱单分类
     *
     * @param stockApplicationPackageCategory
     */
    void insertStockPackageCategory(StockApplicationPackageCategory stockApplicationPackageCategory);

    /**
     * 装箱单
     *
     * @param stockApplicationPackage
     */
    void insertStockApplicationPackage(StockApplicationPackage stockApplicationPackage);

    /**
     * 批量添加装箱单记录
     *
     * @param stockApplicationPackageRecordList
     */
    void insertStockApplicationPackageRecordList(List<StockApplicationPackageRecord> stockApplicationPackageRecordList);

}
