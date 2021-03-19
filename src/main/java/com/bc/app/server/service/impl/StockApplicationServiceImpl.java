package com.bc.app.server.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.*;
import com.bc.app.server.entity.vo.StockApplicationVo;
import com.bc.app.server.enums.StockApplyStatusEnum;
import com.bc.app.server.mapper.*;
import com.bc.app.server.service.StockApplicationService;
import com.bc.app.server.utils.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 出入库
 *
 * @author whl
 */
@Service("stockApplicationService")
public class StockApplicationServiceImpl implements StockApplicationService {

    @Resource
    private StockApplicationMapper stockApplicationMapper;

    @Resource
    private StockApplicationInRecordMapper stockApplicationInRecordMapper;

    @Resource
    private StockApplicationOrderMapper stockApplicationOrderMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private GoodsSpecMapper goodsSpecMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(StockApplication stockApplication, String specNums) {
        if (Constant.STOCK_TYPE_IN.equals(stockApplication.getStockType())) {
            insertStockIn(stockApplication, specNums);
        }
        stockApplicationMapper.insert(stockApplication);
    }

    @Override
    @Transactional
    public void update(StockApplication stockApplication, String specNums) {
        if (Constant.STOCK_TYPE_IN.equals(stockApplication.getStockType())) {
            updateStockIn(stockApplication, specNums);
        }
        stockApplicationMapper.update(stockApplication);
    }

    /**
     * 新增入库
     *
     * @param stockApplication
     * @param specNums
     */
    private void insertStockIn(StockApplication stockApplication, String specNums) {
        String stockApplicationId = stockApplication.getId();
        // 获取订单币种和价格 主题和往来物料号
        String specPrice = Constant.INIT_PRICE;
        String currency = Constant.CURRENCY_RMB;
        if (!StringUtils.isEmpty(stockApplication.getOrderId())) {
            Map orderExtMap = stockApplicationMapper.getOrderExtInfo(stockApplication.getOrderId());
            if (orderExtMap != null) {
                currency = orderExtMap.get("currency").toString();
                if ("AVERAGE".equals(orderExtMap.get("pricingType"))) {
                    specPrice = orderExtMap.get("goodsPrice").toString();
                }
                if (orderExtMap.get("materialNumber") != null) {
                    stockApplication.setMaterialNumber(orderExtMap.get("materialNumber").toString());
                }
                if (orderExtMap.get("po") != null) {
                    stockApplication.setPo(orderExtMap.get("po").toString());
                }
            }
        }
        // 虚拟分类
        StockApplicationPackageCategory c = new StockApplicationPackageCategory();
        c.setId(CommonUtil.generateId());
        c.setBoxCount("1");
        c.setBoxNum("1");
        c.setEnterpriseId(stockApplication.getEnterpriseId());
        c.setCreateUserId(stockApplication.getCreateUserId());
        c.setCount("1");
        c.setStockApplicationId(stockApplicationId);
        // 虚拟箱子
        StockApplicationPackage p = new StockApplicationPackage();
        p.setId(CommonUtil.generateId());
        p.setCreateUserId(stockApplication.getCreateUserId());
        p.setCategoryId(c.getId());
        p.setStockApplicationId(stockApplicationId);
        p.setBoxCount("1");
        p.setBoxNum("1");
        p.setEnterpriseId(stockApplication.getEnterpriseId());
        // 入库明细
        insertStockInDetail(specNums, stockApplication, specPrice, currency, c.getId(), p.getId());
        stockApplicationMapper.insertStockPackageCategory(c);
        stockApplicationMapper.insertStockApplicationPackage(p);
    }

    /**
     * 修改入库
     *
     * @param stockApplication
     * @param specNums
     */
    private void updateStockIn(StockApplication stockApplication, String specNums) {
        String stockApplicationId = stockApplication.getId();
        // 获取订单币种和价格 主题和往来物料号
        String specPrice = Constant.INIT_PRICE;
        String currency = Constant.CURRENCY_RMB;
        if (!StringUtils.isEmpty(stockApplication.getOrderId())) {
            Map orderExtMap = stockApplicationMapper.getOrderExtInfo(stockApplication.getOrderId());
            if (orderExtMap != null) {
                currency = orderExtMap.get("currency").toString();
                if ("AVERAGE".equals(orderExtMap.get("pricingType"))) {
                    specPrice = orderExtMap.get("goodsPrice").toString();
                }
                if (orderExtMap.get("materialNumber") != null) {
                    stockApplication.setMaterialNumber(orderExtMap.get("materialNumber").toString());
                }
                if (orderExtMap.get("po") != null) {
                    stockApplication.setPo(orderExtMap.get("po").toString());
                }
            }
        }
        // 虚拟分类
        StockApplicationPackageCategory c = new StockApplicationPackageCategory();
        c.setStockApplicationId(stockApplicationId);
        c = stockApplicationMapper.selectStockPackageCategory(c);
        // 虚拟箱子
        StockApplicationPackage p = new StockApplicationPackage();
        p.setStockApplicationId(stockApplicationId);
        p = stockApplicationMapper.selectStockApplicationPackage(p);
        // 修改入库明细
        updateStockInDetail(specNums, stockApplication, specPrice, currency, c.getId(), p.getId());
    }

    /**
     * 新增入库明细
     */
    private void insertStockInDetail(String specNums, StockApplication stockApplication, String specPrice, String currency, String categoryId, String packageId) {
        String stockApplicationId = stockApplication.getId();
        List<StockApplicationPackageRecord> packageRecordList = new ArrayList<>();
        List<StockApplicationInRecord> recordList = new ArrayList<>();
        List<StockApplicationOrder> sOrderList = new ArrayList<>();
        if (!StringUtils.isEmpty(specNums)) {
            List<Map> inRecordList = JSONArray.parseArray(specNums, Map.class);
            if (CollectionUtils.isNotEmpty(inRecordList)) {
                // 虚拟箱子记录
                int i = 1;
                String totalCount = "0";
                String totalPrice = "0";
                for (Map m : inRecordList) {
                    // 入库记录
                    StockApplicationInRecord r = new StockApplicationInRecord();
                    r.setId(CommonUtil.generateId());
                    r.setCreateUserId(stockApplication.getCreateUserId());
                    r.setEnterpriseId(stockApplication.getEnterpriseId());
                    if (StringUtils.isEmpty(m.get("applyNum").toString())) {
                        r.setApplyNumber("0");
                    } else {
                        r.setApplyNumber(m.get("applyNum").toString());
                    }
                    r.setGoodsSpecId(m.get("id").toString());
                    r.setGoodsId(stockApplication.getStockGoodsId());
                    r.setStockApplicationId(stockApplicationId);
                    r.setPrice(specPrice);
                    r.setCurrency(currency);
                    r.setBoxId(packageId);
                    r.setSort(i++);
                    if (StringUtils.isEmpty(r.getWareHouseId())) {
                        r.setWareHouseId("0");
                    }
                    recordList.add(r);
                    if (!StringUtils.isEmpty(r.getCount())) {
                        totalCount = new BigDecimal(totalCount).add(new BigDecimal(r.getCount())).toString();
                    }

                    // 装箱单记录
                    StockApplicationPackageRecord pr = new StockApplicationPackageRecord();
                    pr.setId(CommonUtil.generateId());
                    pr.setCount(r.getCount());
                    pr.setApplyNumber(r.getCount());
                    pr.setEnterpriseId(stockApplication.getEnterpriseId());
                    pr.setCreateUserId(stockApplication.getCreateUserId());
                    pr.setGoodsId(stockApplication.getStockGoodsId());
                    pr.setGoodsSpecId(r.getGoodsSpecId());
                    pr.setBoxId(packageId);
                    pr.setSort(i++);
                    pr.setStockApplicationId(stockApplicationId);
                    packageRecordList.add(pr);
                    // 订单明细
                    if (!StringUtils.isEmpty(stockApplication.getOrderId())) {
                        StockApplicationOrder sOrder = new StockApplicationOrder();
                        sOrder.setId(CommonUtil.generateId());
                        sOrder.setStockApplicationId(stockApplicationId);
                        sOrder.setCreateUserId(stockApplication.getCreateUserId());
                        sOrder.setSpecPrice(specPrice);
                        sOrder.setSort(i++);
                        sOrder.setBoxId(packageId);
                        sOrder.setCategoryId(categoryId);
                        sOrder.setCurrency(currency);
                        sOrder.setRecordId(r.getId());
                        sOrder.setSpecId(stockApplication.getStockGoodsId());
                        sOrder.setGoodsId(stockApplication.getStockGoodsId());
                        sOrder.setOrderId(stockApplication.getOrderId());
                        sOrder.setStockNumber(r.getCount());
                        sOrder.setGiveNumber(r.getCount());
                        sOrder.setSurplusNumber("0");
                        sOrder.setEnterpriseId(stockApplication.getEnterpriseId());
                        if (StringUtils.isEmpty(sOrder.getWareHouseId())) {
                            sOrder.setWareHouseId("0");
                        }
                        if (!StringUtils.isEmpty(r.getCount()) && !StringUtils.isEmpty(specPrice)) {
                            totalPrice = new BigDecimal(r.getCount()).multiply(new BigDecimal(specPrice)).setScale(2, RoundingMode.HALF_UP).toString();
                        }
                        sOrderList.add(sOrder);
                    }
                }
                stockApplication.setStockNumber(totalCount);
                stockApplication.setApplyNumber(totalCount);
                stockApplication.setTotalAmount(totalPrice);
                stockApplication.setCostPrice(totalPrice);
                stockApplication.setTax("0");
                stockApplication.setCurrency(currency);
            }
        }
        if (CollectionUtils.isNotEmpty(sOrderList)) {
            //出入库申请和订单关联表
            stockApplicationOrderMapper.insertList(sOrderList);
        }
        if (CollectionUtils.isNotEmpty(recordList)) {
            //入库记录
            stockApplicationInRecordMapper.insertList(recordList);
        }
        if (CollectionUtils.isNotEmpty(packageRecordList)) {
            //批量添加装箱单记录
            stockApplicationMapper.insertStockApplicationPackageRecordList(packageRecordList);
        }
    }

    /**
     * 新增入库明细
     */
    private void updateStockInDetail(String specNums, StockApplication stockApplication, String specPrice, String currency, String categoryId, String packageId) {
        String stockApplicationId = stockApplication.getId();
        List<StockApplicationPackageRecord> packageRecordList = new ArrayList<>();
        List<StockApplicationInRecord> recordList = new ArrayList<>();
        List<StockApplicationOrder> sOrderList = new ArrayList<>();
        if (!StringUtils.isEmpty(specNums)) {
            List<Map> inRecordList = JSONArray.parseArray(specNums, Map.class);
            if (CollectionUtils.isNotEmpty(inRecordList)) {
                // 虚拟箱子记录
                int i = 1;
                String totalCount = "0";
                String totalPrice = "0";
                for (Map m : inRecordList) {
                    // 入库记录
                    StockApplicationInRecord r = new StockApplicationInRecord();
                    r.setId(CommonUtil.generateId());
                    r.setCreateUserId(stockApplication.getCreateUserId());
                    r.setEnterpriseId(stockApplication.getEnterpriseId());
                    if (StringUtils.isEmpty(m.get("applyNum").toString())) {
                        r.setApplyNumber("0");
                    } else {
                        r.setApplyNumber(m.get("applyNum").toString());
                    }
                    r.setGoodsSpecId(m.get("id").toString());
                    r.setGoodsId(stockApplication.getStockGoodsId());
                    r.setStockApplicationId(stockApplicationId);
                    r.setPrice(specPrice);
                    r.setCurrency(currency);
                    r.setBoxId(packageId);
                    r.setSort(i++);
                    if (StringUtils.isEmpty(r.getWareHouseId())) {
                        r.setWareHouseId("0");
                    }
                    recordList.add(r);
                    if (!StringUtils.isEmpty(r.getCount())) {
                        totalCount = new BigDecimal(totalCount).add(new BigDecimal(r.getCount())).toString();
                    }

                    // 装箱单记录
                    StockApplicationPackageRecord pr = new StockApplicationPackageRecord();
                    pr.setId(CommonUtil.generateId());
                    pr.setCount(r.getCount());
                    pr.setApplyNumber(r.getCount());
                    pr.setEnterpriseId(stockApplication.getEnterpriseId());
                    pr.setCreateUserId(stockApplication.getCreateUserId());
                    pr.setGoodsId(stockApplication.getStockGoodsId());
                    pr.setGoodsSpecId(r.getGoodsSpecId());
                    pr.setBoxId(packageId);
                    pr.setSort(i++);
                    pr.setStockApplicationId(stockApplicationId);
                    packageRecordList.add(pr);
                    // 订单明细
                    if (!StringUtils.isEmpty(stockApplication.getOrderId())) {
                        StockApplicationOrder sOrder = new StockApplicationOrder();
                        sOrder.setId(CommonUtil.generateId());
                        sOrder.setStockApplicationId(stockApplicationId);
                        sOrder.setCreateUserId(stockApplication.getCreateUserId());
                        sOrder.setSpecPrice(specPrice);
                        sOrder.setSort(i++);
                        sOrder.setBoxId(packageId);
                        sOrder.setCategoryId(categoryId);
                        sOrder.setCurrency(currency);
                        sOrder.setRecordId(r.getId());
                        sOrder.setSpecId(stockApplication.getStockGoodsId());
                        sOrder.setGoodsId(stockApplication.getStockGoodsId());
                        sOrder.setOrderId(stockApplication.getOrderId());
                        sOrder.setStockNumber(r.getCount());
                        sOrder.setGiveNumber(r.getCount());
                        sOrder.setSurplusNumber("0");
                        sOrder.setEnterpriseId(stockApplication.getEnterpriseId());
                        if (StringUtils.isEmpty(sOrder.getWareHouseId())) {
                            sOrder.setWareHouseId("0");
                        }
                        if (!StringUtils.isEmpty(r.getCount()) && !StringUtils.isEmpty(specPrice)) {
                            totalPrice = new BigDecimal(r.getCount()).multiply(new BigDecimal(specPrice)).setScale(2, RoundingMode.HALF_UP).toString();
                        }
                        sOrderList.add(sOrder);
                    }
                }
                stockApplication.setStockNumber(totalCount);
                stockApplication.setApplyNumber(totalCount);
                stockApplication.setTotalAmount(totalPrice);
                stockApplication.setCostPrice(totalPrice);
                stockApplication.setTax("0");
                stockApplication.setCurrency(currency);
            }
        }
        if (CollectionUtils.isNotEmpty(sOrderList)) {
            //出入库申请和订单关联表
            StockApplicationOrder sOrder = new StockApplicationOrder();
            sOrder.setStockApplicationId(stockApplicationId);
            sOrder.setCreateUserId(stockApplication.getCreateUserId());
            sOrder.setEnterpriseId(stockApplication.getEnterpriseId());
            stockApplicationOrderMapper.deleteBatchStockApplicationOrder(sOrder);
            stockApplicationOrderMapper.insertList(sOrderList);
        }
        if (CollectionUtils.isNotEmpty(recordList)) {
            //入库记录
            StockApplicationInRecord r = new StockApplicationInRecord();
            r.setCreateUserId(stockApplication.getCreateUserId());
            r.setEnterpriseId(stockApplication.getEnterpriseId());
            r.setStockApplicationId(stockApplicationId);
            stockApplicationInRecordMapper.deleteBatchStockApplicationInRecord(r);
            stockApplicationInRecordMapper.insertList(recordList);
        }
        if (CollectionUtils.isNotEmpty(packageRecordList)) {
            //批量添加装箱单记录
            StockApplicationPackageRecord pr = new StockApplicationPackageRecord();
            pr.setEnterpriseId(stockApplication.getEnterpriseId());
            pr.setCreateUserId(stockApplication.getCreateUserId());
            pr.setStockApplicationId(stockApplicationId);
            stockApplicationMapper.deleteBatchStockApplicationPackageRecord(pr);
            stockApplicationMapper.insertStockApplicationPackageRecordList(packageRecordList);
        }
    }

    /**
     * 获取出入库申请分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 每页数量
     * @return 出入库申请分页信息
     */
    @Override
    public PageInfo<StockApplication> getStockApplicationList(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StockApplication> stockApplicationList = stockApplicationMapper.getStockApplicationList(paramMap);
        return new PageInfo<>(stockApplicationList);
    }

    @Override
    public StockApplicationVo findById(String id) {
        StockApplicationVo stockApplication = stockApplicationMapper.findById(id);
        List<StockApplicationInRecord> stockApplicationInRecordList = stockApplicationInRecordMapper.findByStockApplicationId(id);
        if (CollectionUtils.isNotEmpty(stockApplicationInRecordList)) {
            stockApplication.setStockApplicationInRecordList(stockApplicationInRecordList);
        }
        return stockApplication;
    }

    /**
     * 通过物品id查询库存列表
     *
     * @param goodsId 物品id
     * @return
     */
    @Override
    public Goods getStockByGoodsId(String goodsId) {
        Goods goods = new Goods();
        goods = goodsMapper.selectById(goodsId);
        List<StockApplicationInRecord> stock = stockApplicationMapper.getStockByGoodsId(goodsId);
        goods.setStock(stock);
        List<WareHouse> wareHouseList = stockApplicationMapper.getStockDetailByGoodsId(goodsId);
        if (CollectionUtils.isNotEmpty(wareHouseList)) {
            for (WareHouse wareHouse : wareHouseList) {
                if (CollectionUtils.isNotEmpty(wareHouse.getStockApplicationInRecordList())) {
                    List<StockApplicationInRecord> stockApplicationInRecordList = wareHouse.getStockApplicationInRecordList();
                    for (StockApplicationInRecord stockApplicationInRecord : stockApplicationInRecordList) {
                        stockApplicationInRecord.setCreateTime(CommonUtil.stringDateToResule(stockApplicationInRecord.getCreateTime()));
                    }
                }
            }
        }
        goods.setWareHouseList(wareHouseList);
        return goods;
    }

    /**
     * 修改出入库详情
     *
     * @param stockApplication 详情信息
     */
    @Override
    public void updateStockApplication(StockApplication stockApplication) {
        if (StockApplyStatusEnum.STOCK_APPLY_STATUS_CONFIRMED.getStatus().equals(stockApplication.getApplyStatus())) {
            stockApplication.setStatus("1");
        } else if (StockApplyStatusEnum.STOCK_APPLY_STATUS_REJECT.getValue().equals(stockApplication.getApplyStatus())) {
            stockApplication.setStatus("0");
        }
        stockApplicationMapper.update(stockApplication);
        List<StockApplicationInRecord> stockApplicationInRecordList = stockApplication.getStockApplicationInRecordList();
        if (CollectionUtils.isNotEmpty(stockApplicationInRecordList)) {
            stockApplicationInRecordMapper.updateList(stockApplicationInRecordList);
        }
        if (StockApplyStatusEnum.STOCK_APPLY_STATUS_CONFIRMED.getValue().equals("")) {
            goodsMapper.updateResidualNumberByGoodsId(stockApplication.getStockGoodsId());
            goodsSpecMapper.updateGoodsSpecResidualNumberByGoodsId(stockApplication.getStockGoodsId());
        }
    }

}
