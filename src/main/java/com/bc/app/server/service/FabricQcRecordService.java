package com.bc.app.server.service;

import com.bc.app.server.entity.FabricQcRecord;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;

import java.util.List;
import java.util.Map;


/**
 * 面料检查记录
 */
public interface FabricQcRecordService {


    /**
     * 通过id查询数据
     *
     * @param fabricQcRecord
     * @return
     */
    FabricQcRecord selectById(FabricQcRecord fabricQcRecord);

    List<FabricQcRecord> getByWarehouseId(FabricQcRecord fabricQcRecord);

    /**
     * 集合新增
     *
     * @param fabricQcRecord
     * @return
     */
    void addListRecord(FabricQcRecord fabricQcRecord);


    Integer updateListById(List<UpdateByIdVo> list);

    Integer updateById(Map<String, String> map);

    GetByWarehouseIdVo getCountData(FabricQcRecord fabricQcRecord);
}
