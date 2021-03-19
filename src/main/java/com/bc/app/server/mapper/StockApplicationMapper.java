package com.bc.app.server.mapper;


import com.bc.app.server.entity.*;
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
     * @param paramMap
     * @return
     */
    List<StockApplication> getStockApplicationList(Map<String, Object> paramMap);

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

    /**
     * 更新数据
     *
     * @param stockApplication 入参
     */
    void update(StockApplication stockApplication);

    /**
     * 根据stockApplicationId查询t_stock_application_package_category列表信息
     *
     * @param stockApplicationPackageCategory 入参
     * @return
     */
    StockApplicationPackageCategory selectStockPackageCategory(StockApplicationPackageCategory stockApplicationPackageCategory);

    /**
     * 根据stockApplicationId查询列表信息
     *
     * @param stockApplicationPackage 入参
     * @return
     */
    StockApplicationPackage selectStockApplicationPackage(StockApplicationPackage stockApplicationPackage);

    /**
     * 根据条件更新数据
     *
     * @param packageRecordList 入参
     */
    void updateStockApplicationPackageRecordList(List<StockApplicationPackageRecord> packageRecordList);

    /**
     * 通过条件批量删除StockApplicationPackageRecord数据
     *
     * @param pr 入参
     */
    void deleteBatchStockApplicationPackageRecord(StockApplicationPackageRecord pr);

    /**
     * 通过商品id 获取入库记录信息列表
     *
     * @param goodsId 商品id
     * @return 入库记录信息列表
     */
    List<StockApplicationInRecord> getStockByGoodsId(String goodsId);

    /**
     * 根据商品id 查询对应的仓库列表
     *
     * @param goodsId 商品id
     * @return 仓库列表
     */
    List<WareHouse> getStockDetailByGoodsId(String goodsId);
}
