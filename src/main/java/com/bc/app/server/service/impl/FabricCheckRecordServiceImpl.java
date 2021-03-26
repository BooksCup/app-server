package com.bc.app.server.service.impl;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.FabricCheckRecord;
import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.entity.ProblemImageClassify;
import com.bc.app.server.mapper.FabricCheckRecordMapper;
import com.bc.app.server.mapper.FabricCheckRecordProblemMapper;
import com.bc.app.server.mapper.FabricCheckTaskMapper;
import com.bc.app.server.service.FabricCheckRecordService;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.FabricCheckRecordSearchAllVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.FabricQcRecordAllByCheckLIIdVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    FabricCheckTaskMapper fabricCheckTaskMapper;

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
    public void insertFabricCheckRecord(FabricCheckRecord fabricCheckRecord) {
        fabricCheckRecordMapper.insert(fabricCheckRecord);
    }

    @Transactional
    @Override
    public Integer batchUpdateFabricCheckRecordByIds(List<FabricCheckRecord> list) {
        return fabricCheckRecordMapper.batchUpdateFabricCheckRecordByIds(list);
    }

    @Override
    public Integer updateById(FabricCheckRecord fabricCheckRecord) {
        return fabricCheckRecordMapper.updateByid(fabricCheckRecord);
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
    public List<FabricCheckRecord> insertFabricQcRecords(List<FabricCheckRecord> list, String modifyTime, String fabricCheckTaskId) {
        if ("modifyTimeExamine".equals(modifyTime)) {//审核完毕
            if (CollectionUtils.isNotEmpty(list)) {
                List<FabricCheckRecord> noIdList = new ArrayList<>();
                List<FabricCheckRecord> haveIdList = new ArrayList<>();
                for (FabricCheckRecord fabricCheckRecord : list) {
                    if (StringUtils.isEmpty(fabricCheckRecord.getId())) {
                        fabricCheckRecord.setId(CommonUtil.generateId());
                        noIdList.add(fabricCheckRecord);
                    } else {
                        haveIdList.add(fabricCheckRecord);
                    }
                }
                //有id的更新，
                if (CollectionUtils.isNotEmpty(haveIdList)) {
                    fabricCheckRecordMapper.batchUpdateFabricCheckRecordByIds(haveIdList);
                }
                //没有id的保存
                if (CollectionUtils.isNotEmpty(noIdList)) {
                    fabricCheckRecordMapper.insertFabricQcRecords(noIdList);
                }
            }
        } else {
            if (CollectionUtils.isNotEmpty(list)) {
                fabricCheckRecordMapper.deleteByCheckLotInfoId(list.get(0).getCheckLotInfoId());
                for (FabricCheckRecord fabricCheckRecord : list) {
                    fabricCheckRecord.setId(CommonUtil.generateId());
                }
                fabricCheckRecordMapper.insertFabricQcRecords(list);
            }
        }
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("id", fabricCheckTaskId);
        if ("modifyTimeApply".equals(modifyTime)) {
            map.put("modifyTimeApply", "modifyTimeApply");
            fabricCheckTaskMapper.updateById(map);
        } else if ("modifyTimeExamine".equals(modifyTime)) {
            map.put("modifyTimeExamine", "modifyTimeExamine");
            fabricCheckTaskMapper.updateById(map);
        }
        return list;
    }


    @Override
    public void insert(FabricCheckRecord fabricCheckRecord, String modifyTime, String fabricCheckTaskId) {
        fabricCheckRecordMapper.insert(fabricCheckRecord);
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("id", fabricCheckTaskId);
        if ("modifyTimeApply".equals(modifyTime)) {
            map.put("modifyTimeApply", "modifyTimeApply");
            fabricCheckTaskMapper.updateById(map);
        } else if ("modifyTimeExamine".equals(modifyTime)) {
            map.put("modifyTimeExamine", "modifyTimeExamine");
            fabricCheckTaskMapper.updateById(map);
        }
    }

    /**
     * 通过面料盘点-缸信息表id获取检查记录表信息
     *
     * @param map 入参
     * @return 检查记录表信息集合
     */
    @Override
    public FabricCheckRecordSearchAllVo getFabricQcRecordAllByCheckLIId(Map<String, String> map) {
        FabricCheckRecordSearchAllVo fabricCheckRecordSearchAllVo = new FabricCheckRecordSearchAllVo();
        List<FabricQcRecordAllByCheckLIIdVo> list = fabricCheckRecordMapper.getFabricQcRecordGroupDeliveryDates(map);
        List<String> recordIdList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(list)) {
            for (FabricQcRecordAllByCheckLIIdVo f : list) {
                map.put("deliveryDate", f.getDeliveryDate());
                List<FabricCheckRecord> fabricCheckRecordList = fabricCheckRecordMapper.getFabricQcRecordAllByCheckLIId(map);
                f.setFabricCheckRecords(fabricCheckRecordList);
                if (CollectionUtils.isNotEmpty(fabricCheckRecordList)) {
                    for (FabricCheckRecord fabricCheckRecord : fabricCheckRecordList) {
                        if (!ObjectUtils.isEmpty(fabricCheckRecord) && !StringUtils.isEmpty(fabricCheckRecord.getSno())) {
                            fabricCheckRecord.setSno(fabricCheckRecord.getSno().replace(".","-"));
                        }
                        FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem();
                        recordIdList.add(fabricCheckRecord.getId());
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
        List<ProblemImageClassify> problemImageClassifyList = fabricCheckRecordProblemMapper.getCountByTag(recordIdList);
        List<ProblemImageClassify> problemImageClassifiesResult = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(problemImageClassifyList)) {
            for (ProblemImageClassify ProblemImageClassify : problemImageClassifyList) {
                String images = ProblemImageClassify.getImages();
                images = images.replace("[]", "").replace(">", "").replace("][", ",");
                ProblemImageClassify.setImages(images);
                problemImageClassifiesResult.add(ProblemImageClassify);
            }
        }
        fabricCheckRecordSearchAllVo.setProblemImageClassifyList(problemImageClassifiesResult);
        fabricCheckRecordSearchAllVo.setFabricQcRecordAllByCheckLIIdVoList(list);
        return fabricCheckRecordSearchAllVo;
    }

    Integer parseStrToInte(String string) {
        if (null == string || "".equals(string)) {
            return 0;
        }
        return Integer.valueOf(string);
    }

}