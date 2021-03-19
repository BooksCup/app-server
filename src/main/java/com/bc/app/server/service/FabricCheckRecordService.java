package com.bc.app.server.service;

import com.bc.app.server.entity.FabricCheckRecord;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.FabricCheckRecordSearchAllVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.FabricQcRecordAllByCheckLIIdVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;

import java.util.List;
import java.util.Map;


/**
 * 面料检查记录
 *
 * @author qiu
 */
public interface FabricCheckRecordService {


    /**
     * 通过id查询数据
     *
     * @param fabricCheckRecord
     * @return
     */
    FabricCheckRecord selectById(FabricCheckRecord fabricCheckRecord);

    List<FabricCheckRecord> getByWarehouseId(FabricCheckRecord fabricCheckRecord);

    /**
     *
     * @param fabricCheckRecord
     * @return
     */
    void insertFabricCheckRecord(FabricCheckRecord fabricCheckRecord);


    Integer batchUpdateFabricCheckRecordByIds(List<FabricCheckRecord> list);

    Integer updateById(FabricCheckRecord fabricCheckRecord);

    GetByWarehouseIdVo getCountData(FabricCheckRecord fabricCheckRecord);

    /**
     * 批量保存检查记录信息
     *
     * @param list 检查记录信息集合
     * @return
     */
    List<FabricCheckRecord> insertFabricQcRecords(List<FabricCheckRecord> list, String modifyTime, String fabricCheckTaskId);

    /**
     * 通过面料盘点-缸信息表id获取检查记录表信息
     *
     * @param map 入参
     * @return 检查记录表信息集合
     */
    FabricCheckRecordSearchAllVo getFabricQcRecordAllByCheckLIId(Map<String, String> map);

    /**
     * 保存单个记录信息
     * @param fabricCheckRecord   记录信息
     * @param modifyTime
     * @param fabricCheckTaskId
     */
    void insert(FabricCheckRecord fabricCheckRecord, String modifyTime, String fabricCheckTaskId);
}