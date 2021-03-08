package com.bc.app.server.service;

import com.bc.app.server.entity.FabricCheckRecord;
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
     * 集合新增
     *
     * @param fabricCheckRecord
     * @return
     */
    void addListRecord(FabricCheckRecord fabricCheckRecord);


    Integer batchUpdateFabricCheckRecordByIds(List<FabricCheckRecord> list);

    Integer updateById(Map<String, String> map);

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
    List<FabricQcRecordAllByCheckLIIdVo> getFabricQcRecordAllByCheckLIId(Map<String, String> map);

}