package com.bc.app.server.service.impl;

import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.entity.StockApplicationInRecord;
import com.bc.app.server.entity.StockApplicationOrder;
import com.bc.app.server.mapper.StockApplicationInRecordMapper;
import com.bc.app.server.mapper.StockApplicationMapper;
import com.bc.app.server.mapper.StockApplicationOrderMapper;
import com.bc.app.server.service.StockApplicationService;
import com.bc.app.server.utils.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void insert(StockApplication stockApplication) {
        String stockApplicationId = CommonUtil.generateId();
        stockApplication.setId(stockApplicationId);
        if (CollectionUtils.isNotEmpty(stockApplication.getStockApplicationInRecordList())) {
            int i = 1;
            for (StockApplicationInRecord r : stockApplication.getStockApplicationInRecordList()) {
                r.setId(CommonUtil.generateId());
                r.setEnterpriseId(stockApplication.getEnterpriseId());
                r.setSort(i++);
                r.setCreateUserId(stockApplication.getCreateUserId());
                r.setApplyNumber(r.getCount());
                r.setStockApplicationId(stockApplicationId);
                r.setPrice("0");
            }
            stockApplicationInRecordMapper.insertList(stockApplication.getStockApplicationInRecordList());
        }
        if (CollectionUtils.isNotEmpty(stockApplication.getStockApplicationOrderList())) {
            int i = 1;
            for (StockApplicationOrder r : stockApplication.getStockApplicationOrderList()) {
                r.setId(CommonUtil.generateId());
                r.setEnterpriseId(stockApplication.getEnterpriseId());
                r.setSort(i++);
                r.setCreateUserId(stockApplication.getCreateUserId());
                r.setStockApplicationId(stockApplicationId);
                r.setSpecPrice("0");
            }
            stockApplicationOrderMapper.insertList(stockApplication.getStockApplicationOrderList());
        }
        stockApplicationMapper.insert(stockApplication);
    }

    @Override
    public List<StockApplication> getStockApplicationList(Map<String, Object> paramsMap) {
        return stockApplicationMapper.getStockApplicationList(paramsMap);
    }

    @Override
    public StockApplication findById(String id) {
        StockApplication stockApplication = stockApplicationMapper.findById(id);
        List<StockApplicationInRecord> stockApplicationInRecordList = stockApplicationInRecordMapper.findByStockApplicationId(id);
        if (CollectionUtils.isNotEmpty(stockApplicationInRecordList)) {
            stockApplication.setStockApplicationInRecordList(stockApplicationInRecordList);
        }
        List<StockApplicationOrder> stockApplicationOrderList = stockApplicationOrderMapper.findByStockApplicationId(id);
        if (CollectionUtils.isNotEmpty(stockApplicationOrderList)) {
            stockApplication.setStockApplicationOrderList(stockApplicationOrderList);
        }
        return stockApplication;
    }
}
