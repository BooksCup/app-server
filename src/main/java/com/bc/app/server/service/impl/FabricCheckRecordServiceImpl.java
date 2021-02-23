package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricCheckRecord;
import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.mapper.FabricCheckRecordMapper;
import com.bc.app.server.mapper.FabricCheckRecordProblemMapper;
import com.bc.app.server.service.FabricCheckRecordService;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.FabricQcRecordAllByCheckLIIdVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * 面料验货问题配置
 */
@Service("fabricQcRecordService")
public class FabricCheckRecordServiceImpl implements FabricCheckRecordService {


    @Autowired
    private FabricCheckRecordMapper fabricCheckRecordMapper;
    @Autowired
    FabricCheckRecordProblemMapper fabricCheckRecordProblemMapper;

    /**
     * 通过id查询数据
     *
     * @param fabricCheckRecord
     * @return
     */
    @Override
    public FabricCheckRecord selectById(FabricCheckRecord fabricCheckRecord) {
        return fabricCheckRecordMapper.selectById(fabricCheckRecord);
    }

    @Override
    public List<FabricCheckRecord> getByWarehouseId(FabricCheckRecord fabricCheckRecord) {
        return fabricCheckRecordMapper.getByWarehouseId(fabricCheckRecord);
    }

    /**
     * 集合新增
     *
     * @param fabricCheckRecord
     * @return
     */
    @Override
    public void addListRecord(FabricCheckRecord fabricCheckRecord) {
        fabricCheckRecordMapper.addRecord(fabricCheckRecord);
    }

    @Transactional
    @Override
    public Integer updateListById(List<UpdateByIdVo> list) {
        return fabricCheckRecordMapper.updateListById(list);
    }

    @Override
    public Integer updateById(Map<String, String> map) {
        return fabricCheckRecordMapper.updateByid(map);
    }

    @Override
    public GetByWarehouseIdVo getCountData(FabricCheckRecord fabricCheckRecord) {
        return fabricCheckRecordMapper.getCountData(fabricCheckRecord);
    }

    /**
     * 批量保存检查记录信息
     *
     * @param list 检查记录信息集合
     * @return
     */
    @Override
    public void insertFabricQcRecords(List<FabricCheckRecord> list) {
        if (CollectionUtils.isNotEmpty(list)){
            String checkLotInfoId = list.get(0).getCheckLotInfoId();
            fabricCheckRecordMapper.deleteByCheckLotInfoId(checkLotInfoId);
            for (FabricCheckRecord fabricCheckRecord:list){
                fabricCheckRecord.setId(CommonUtil.generateId());
            }
        }
        fabricCheckRecordMapper.insertFabricQcRecords(list);
    }

//    /**
//     * 通过面料盘点-缸信息表id获取检查记录表信息
//     *
//     * @param map 入参
//     * @return 检查记录表信息集合
//     */
//    @Override
//    public List<FabricQcRecordAllByCheckLIIdVo> getFabricQcRecordAllByCheckLIId(Map<String, String> map) {
//        List<FabricQcRecordAllByCheckLIIdVo> list = fabricCheckRecordMapper.getFabricQcRecordGroupDeliveryDates(map);
//        if (CollectionUtils.isNotEmpty(list)) {
//            for (FabricQcRecordAllByCheckLIIdVo f : list) {
//                map.put("deliveryDates", f.getDeliveryDates());
//                List<FabricCheckRecord> fabricQcRecordList = fabricCheckRecordMapper.getFabricQcRecordAllByCheckLIId(map);
//                f.setFabricCheckRecords(fabricQcRecordList);
//            }
//        }
//        return list;
//    }

    /**
     * 通过面料盘点-缸信息表id获取检查记录表信息
     *
     * @param map 入参
     * @return 检查记录表信息集合
     */
    @Override
    public List<FabricQcRecordAllByCheckLIIdVo> getFabricQcRecordAllByCheckLIId(Map<String, String> map) {
        List<FabricQcRecordAllByCheckLIIdVo> list = fabricCheckRecordMapper.getFabricQcRecordGroupDeliveryDates(map);
        if (CollectionUtils.isNotEmpty(list)) {
            for (FabricQcRecordAllByCheckLIIdVo f : list) {
                map.put("deliveryDate", f.getDeliveryDate());
                List<FabricCheckRecord> fabricCheckRecordList = fabricCheckRecordMapper.getFabricQcRecordAllByCheckLIId(map);
                f.setFabricCheckRecords(fabricCheckRecordList);
                if (CollectionUtils.isNotEmpty(fabricCheckRecordList)) {
                    for (FabricCheckRecord fabricCheckRecord : fabricCheckRecordList) {
                        FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem();
                        fabricCheckRecordProblem.setRecordId(fabricCheckRecord.getId());
                        List<FabricCheckRecordProblem> fabricCheckRecordProblemList = fabricCheckRecordProblemMapper.getCountData(fabricCheckRecordProblem);
                        if (CollectionUtils.isNotEmpty(fabricCheckRecordProblemList)) {
                            fabricCheckRecordProblem = fabricCheckRecordProblemList.get(0);
                            if (null != fabricCheckRecordProblem) {
                                fabricCheckRecord.setFabricCheckRecordProblem(fabricCheckRecordProblem);
                                Integer integer = parseStrToInte(fabricCheckRecordProblem.getTagATimes()) + parseStrToInte(fabricCheckRecordProblem.getTagBTimes()) +
                                        parseStrToInte(fabricCheckRecordProblem.getTagCTimes()) + parseStrToInte(fabricCheckRecordProblem.getTagDTimes());
                                fabricCheckRecord.setProblemCount("" + Integer.valueOf(integer));
                                fabricCheckRecord.setProblemCount(integer + "");
                            }
                        }
                    }
                }
            }
        }
        return list;
    }

    Integer parseStrToInte(String string) {
        if (null == string || "".equals(string)) {
            return 0;
        }
        return Integer.valueOf(string);
    }

}