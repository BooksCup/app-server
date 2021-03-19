package com.bc.app.server.service;

import com.bc.app.server.entity.WareHouse;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author whl
 */

public interface WareHouseService {

    /**
     * 获取出入库申请分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 每页数量
     * @return 出入库申请分页信息
     */
    PageInfo<WareHouse> getWareHouseList(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    /**
     * 仓库详情中的出入列表
     *
     * @param id       仓库id
     * @param pageNum  当前页
     * @param pageSize 每页显示个数
     * @return
     */
    PageInfo<Map<String, String>> getStockInfoList(String id, Integer pageNum, Integer pageSize);

    /**
     * 通过仓库id查询仓库 库存
     *
     * @param id 库存id
     * @return 仓库库存信息
     */
    WareHouse getWareHouseStockById(String id);

    /**
     * 通过parent_id查询二级仓库列表
     *
     * @param parentId 仓库parentId
     * @return 二级仓库列表
     */
    List<WareHouse> getByParentId(String parentId);
}
