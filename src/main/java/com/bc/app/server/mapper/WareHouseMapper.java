package com.bc.app.server.mapper;


import com.bc.app.server.entity.WareHouse;

import java.util.List;
import java.util.Map;

/**
 * @author whl
 */
public interface WareHouseMapper {

    /**
     * 搜索仓库列表
     *
     * @param paramMap
     * @return
     */
    List<WareHouse> getWareHouseList(Map<String, Object> paramMap);

    WareHouse getWareHouseByid(String id);

    /**
     * 通过parent_id查询二级仓库列表
     *
     * @param parentId 仓库parentId
     * @return 二级仓库列表
     */
    List<WareHouse> getByParentId(String parentId);
}
