package com.bc.app.server.service;

import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.entity.vo.StockApplicationVo;
import com.github.pagehelper.PageInfo;

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
     * 获取出入库申请分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 每页数量
     * @return 出入库申请分页信息
     */
    PageInfo<StockApplication> getStockApplicationList(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    StockApplicationVo findById(String id);
}
