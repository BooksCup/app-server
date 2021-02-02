package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricQcRecord;
import com.bc.app.server.mapper.FabricQcRecordMapper;
import com.bc.app.server.service.FabricQcRecordService;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 面料验货问题配置
 */
@Service("fabricQcRecordService")
public class FabricQcRecordServiceImpl implements FabricQcRecordService {


    @Autowired
    private FabricQcRecordMapper fabricQcRecordMapper;


    /**
     * 通过id查询数据
     *
     * @param fabricQcRecord
     * @return
     */
    @Override
    public FabricQcRecord selectById(FabricQcRecord fabricQcRecord) {
        return fabricQcRecordMapper.selectById(fabricQcRecord);
    }

    @Override
    public List<FabricQcRecord> getByWarehouseId(FabricQcRecord fabricQcRecord) {
        return fabricQcRecordMapper.getByWarehouseId(fabricQcRecord);
    }

    /**
     * 集合新增
     *
     * @param fabricQcRecord
     * @return
     */
    @Override
    public void addListRecord(FabricQcRecord fabricQcRecord) {
        fabricQcRecordMapper.addRecord(fabricQcRecord);
    }

    @Transactional
    @Override
    public Integer updateListById(List<UpdateByIdVo> list) {
        return fabricQcRecordMapper.updateListById(list);
    }

    @Override
    public Integer updateById(Map<String, String> map) {
        return fabricQcRecordMapper.updateByid(map);
    }

    @Override
    public GetByWarehouseIdVo getCountData(FabricQcRecord fabricQcRecord) {
        return fabricQcRecordMapper.getCountData(fabricQcRecord);
    }

}