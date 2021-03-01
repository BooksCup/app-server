package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricCheckRecord;
import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.mapper.FabricCheckRecordProblemMapper;
import com.bc.app.server.service.FabricCheckRecordProblemService;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.vo.fabriccheckrecordproblemcontrollervo.FabricCheckRecordProblemVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 面料验货问题配置
 */
@Service("fabricQcRecordProblemService")
public class FabricCheckRecordProblemServiceImpl implements FabricCheckRecordProblemService {


    @Autowired
    private FabricCheckRecordProblemMapper fabricCheckRecordProblemMapper;

    /**
     * 新增问题配置
     *
     * @param fabricCheckRecordProblemList 入参
     */
    @Transactional
    @Override
    public void addFabricQcRecordProblem(List<FabricCheckRecordProblem> fabricCheckRecordProblemList) {
        if (CollectionUtils.isNotEmpty(fabricCheckRecordProblemList)){
            FabricCheckRecordProblem fabricCheckRecordProblem = fabricCheckRecordProblemList.get(0);
            fabricCheckRecordProblemMapper.deleteByRecordId(fabricCheckRecordProblem);
        }
        fabricCheckRecordProblemMapper.addFabricQcRecordProblem(fabricCheckRecordProblemList);
    }

    /**
     * 通过验货表id查询问题集合
     *
     * @param fabricCheckRecordProblem 入参
     */
    @Transactional
    @Override
    public List<FabricCheckRecordProblemVo> getFabricQcRecordProblemByRecordId(FabricCheckRecordProblem fabricCheckRecordProblem) {
        List<FabricCheckRecordProblemVo> fabricCheckRecordProblemVos = fabricCheckRecordProblemMapper.getEntiryGroupByProblemPosition(fabricCheckRecordProblem);
        if (CollectionUtils.isNotEmpty(fabricCheckRecordProblemVos)){
            for (FabricCheckRecordProblemVo fabricCheckRecordProblemVo:fabricCheckRecordProblemVos){
                fabricCheckRecordProblem.setProblemPosition(fabricCheckRecordProblemVo.getProblemPosition());
                FabricCheckRecordProblem fabricCheckRecord = fabricCheckRecordProblemMapper.getEntiryByRecordRdAndPosition(fabricCheckRecordProblem);
                fabricCheckRecordProblemVo.setFabricCheckRecordProblem(fabricCheckRecord);
            }
        }
        return fabricCheckRecordProblemVos;
    }

    /**
     * 删除数据
     *
     * @param idList id集合
     */
    @Override
    public Integer updateByIds(List<FabricCheckRecordProblem> idList) {
        return fabricCheckRecordProblemMapper.updateByIds(idList);
    }

    /**
     * 通过id更新数据
     *
     * @param fabricCheckRecordProblem
     */
    @Override
    public void updateById(FabricCheckRecordProblem fabricCheckRecordProblem) {
        fabricCheckRecordProblemMapper.updateById(fabricCheckRecordProblem);
    }

    /**
     * 通过id查询数据
     *
     * @param fabricCheckRecordProblem
     * @return
     */
    @Override
    public FabricCheckRecordProblem selectById(FabricCheckRecordProblem fabricCheckRecordProblem) {
        return fabricCheckRecordProblemMapper.selectById(fabricCheckRecordProblem);
    }

    /**
     * 通过验货表id查询统计列表
     *
     * @param fabricCheckRecordProblem
     * @return
     */
    @Override
    public List<FabricCheckRecordProblem> getCountData(FabricCheckRecordProblem fabricCheckRecordProblem) {
        return fabricCheckRecordProblemMapper.getCountData(fabricCheckRecordProblem);
    }
}
