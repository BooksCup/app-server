package com.bc.app.server.mapper;

import com.bc.app.server.entity.FabricQcRecord;
import com.bc.app.server.entity.FabricQcWarehouse;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;

import java.util.List;
import java.util.Map;


/**
 * 记录检查信息
 */
public interface FabricQcRecordMapper {


    /**
     * 通过id查询数据
     *
     * @param fabricQcRecord
     * @return
     */
    FabricQcRecord selectById(FabricQcRecord fabricQcRecord);

    List<FabricQcRecord> getByWarehouseId(FabricQcRecord fabricQcRecord);

    /**
     * 新增
     *
     * @param fabricQcRecord
     * @return
     */
    void addRecord(FabricQcRecord fabricQcRecord);

    /**
     * 集合新增
     *
     * @param fabricQcRecords
     * @return
     */
    Integer insertList(List<FabricQcRecord> fabricQcRecords);

    Integer updateByid(Map<String, String> map);

    Integer updateListById(List<UpdateByIdVo> list);

    GetByWarehouseIdVo getCountData(FabricQcRecord fabricQcRecord);

    Integer deleteByWarehouseId(String id);
}
