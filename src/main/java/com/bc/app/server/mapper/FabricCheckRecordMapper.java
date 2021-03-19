package com.bc.app.server.mapper;

import com.bc.app.server.entity.FabricCheckRecord;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.FabricQcRecordAllByCheckLIIdVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;

import java.util.List;
import java.util.Map;


/**
 * 记录检查信息
 */
public interface FabricCheckRecordMapper {


    /**
     * 通过id查询数据
     *
     * @param fabricCheckRecord
     * @return
     */
    FabricCheckRecord selectById(FabricCheckRecord fabricCheckRecord);

    List<FabricCheckRecord> getByWarehouseId(FabricCheckRecord fabricCheckRecord);

    /**
     * 新增
     *
     * @param fabricCheckRecord
     * @return
     */
    void insert(FabricCheckRecord fabricCheckRecord);

    /**
     * 批量保存检查记录信息
     *
     * @param fabricCheckRecords 检查记录信息集合
     * @return
     */
    Integer insertFabricQcRecords(List<FabricCheckRecord> fabricCheckRecords);

    Integer updateByid(FabricCheckRecord fabricCheckRecords);

    Integer batchUpdateFabricCheckRecordByIds(List<FabricCheckRecord> list);

    GetByWarehouseIdVo getCountData(FabricCheckRecord fabricCheckRecord);

    Integer deleteByWarehouseId(String id);

    List<FabricQcRecordAllByCheckLIIdVo> getFabricQcRecordGroupDeliveryDates(Map<String, String> map);

    List<FabricCheckRecord> getFabricQcRecordAllByCheckLIId(Map<String, String> map);

    void deleteByCheckLotInfoId(String checkLotInfoId);
}
