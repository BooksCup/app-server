package com.bc.app.server.service.impl;

import com.bc.app.server.entity.*;
import com.bc.app.server.enums.StockApplicationBizTypeEnum;
import com.bc.app.server.mapper.StockApplicationInRecordMapper;
import com.bc.app.server.mapper.WareHouseMapper;
import com.bc.app.server.service.WareHouseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 出入库
 *
 * @author whl
 */
@Service("wareHouseService")
public class WareHouseServiceImpl implements WareHouseService {

    @Resource
    private WareHouseMapper wareHouseMapper;

    @Autowired
    StockApplicationInRecordMapper stockApplicationInRecordMapper;

    /**
     * 获取出入库申请分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 每页数量
     * @return 出入库申请分页信息
     */
    @Override
    public PageInfo<WareHouse> getWareHouseList(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<WareHouse> wareHouseList = wareHouseMapper.getWareHouseList(paramMap);
        return new PageInfo<>(wareHouseList);
    }

    /**
     * 仓库详情中的出入列表
     *
     * @param wareHouseId 仓库wareHouseId
     * @param pageNum     当前页
     * @param pageSize    每页显示个数
     * @return
     */
    @Override
    public PageInfo<Map<String, String>> getStockInfoList(String wareHouseId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, String>> resultList = stockApplicationInRecordMapper.getStockInfoByWareHouseId(wareHouseId);
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (Map<String, String> map : resultList) {
                if (!StringUtils.isEmpty(map.get("stockType"))) {
                    map.put("stockType", StockApplicationBizTypeEnum.getEnumByValue(map.get("stockType")).getDesc());
                }
            }
        }
        return new PageInfo<>(resultList);
    }

    /**
     * 通过仓库id查询仓库 库存
     *
     * @param id 库存id
     * @return 仓库库存信息
     */
    @Override
    public WareHouse getWareHouseStockById(String id) {
        WareHouse wareHouse = wareHouseMapper.getWareHouseByid(id);
        List<StockApplicationInRecord> stockApplicationInRecordList= stockApplicationInRecordMapper.getStockListByWareHouseId(id);
        wareHouse.setStockApplicationInRecordList(stockApplicationInRecordList);
        return wareHouse;
    }

    /**
     * 通过parent_id查询二级仓库列表
     *
     * @param parentId 仓库parentId
     * @return 二级仓库列表
     */
    @Override
    public List<WareHouse> getByParentId(String parentId) {
        return wareHouseMapper.getByParentId(parentId);
    }
}
