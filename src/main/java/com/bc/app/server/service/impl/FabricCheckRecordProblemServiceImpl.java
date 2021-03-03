package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.entity.FabricCheckRecordProblemPosition;
import com.bc.app.server.mapper.FabricCheckRecordProblemMapper;
import com.bc.app.server.mapper.FabricCheckRecordProblemPositionMapper;
import com.bc.app.server.service.FabricCheckRecordProblemService;
import com.bc.app.server.utils.CommonUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.List;


/**
 * 面料验货问题配置
 */
@Service("fabricQcRecordProblemService")
public class FabricCheckRecordProblemServiceImpl implements FabricCheckRecordProblemService {


    @Autowired
    private FabricCheckRecordProblemMapper fabricCheckRecordProblemMapper;

    @Autowired
    private FabricCheckRecordProblemPositionMapper fabricCheckRecordProblemPositionMapper;

    /**
     * 新增问题配置
     *
     * @param fabricCheckRecordProblemPosition 入参
     */
    @Transactional
    @Override
    public void addFabricQcRecordProblem(FabricCheckRecordProblemPosition fabricCheckRecordProblemPosition) {
        //新增之前根据位置id删除位置表信息和为题表信息
        if (!StringUtils.isEmpty(fabricCheckRecordProblemPosition.getId())) {
            fabricCheckRecordProblemPositionMapper.deleteById(fabricCheckRecordProblemPosition.getId());
            fabricCheckRecordProblemMapper.deleteByProblemPositionId(fabricCheckRecordProblemPosition.getId());
        }
        if (!ObjectUtils.isEmpty(fabricCheckRecordProblemPosition)) {
            fabricCheckRecordProblemPosition.setId(CommonUtil.generateId());
            List<FabricCheckRecordProblem> fabricCheckRecordProblemList = fabricCheckRecordProblemPosition.getFabricCheckRecordProblemList();
            if (CollectionUtils.isNotEmpty(fabricCheckRecordProblemList)) {
                for (FabricCheckRecordProblem fabricCheckRecordProblem : fabricCheckRecordProblemList) {
                    fabricCheckRecordProblem.setId(CommonUtil.generateId());
                    fabricCheckRecordProblem.setProblemPositionId(fabricCheckRecordProblemPosition.getId());
                }
            }
        }
        fabricCheckRecordProblemPositionMapper.addFabricCheckRecordProblemPosition(fabricCheckRecordProblemPosition);
        fabricCheckRecordProblemMapper.addFabricQcRecordProblem(fabricCheckRecordProblemPosition.getFabricCheckRecordProblemList());
    }

    /**
     * 通过验货表id查询问题集合
     *
     * @param fabricCheckRecordProblem 入参
     */
    @Transactional
    @Override
    public List<FabricCheckRecordProblemPosition> getFabricQcRecordProblemByRecordId(FabricCheckRecordProblem fabricCheckRecordProblem) {
        List<FabricCheckRecordProblemPosition> fabricCheckRecordProblemPositionList = fabricCheckRecordProblemPositionMapper.getEntiryGroupByProblemPosition(fabricCheckRecordProblem.getRecordId());
        if (CollectionUtils.isNotEmpty(fabricCheckRecordProblemPositionList)) {
            for (FabricCheckRecordProblemPosition fabricCheckRecordProblemPosition : fabricCheckRecordProblemPositionList) {
                fabricCheckRecordProblem.setProblemPositionId(fabricCheckRecordProblemPosition.getId());
                List<FabricCheckRecordProblem> fabricCheckRecord = fabricCheckRecordProblemMapper.getEntiryByRecordRdAndPosition(fabricCheckRecordProblem);
                fabricCheckRecordProblemPosition.setFabricCheckRecordProblemList(fabricCheckRecord);
            }
        }
        return fabricCheckRecordProblemPositionList;
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
     * @param fabricCheckRecordProblemList
     */
    @Override
    public void updateRecordProblemById(List<FabricCheckRecordProblem> fabricCheckRecordProblemList) {
        fabricCheckRecordProblemMapper.updateRecordProblemById(fabricCheckRecordProblemList);
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
