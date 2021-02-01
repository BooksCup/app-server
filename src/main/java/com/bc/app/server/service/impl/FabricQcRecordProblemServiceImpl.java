package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricQcRecordProblem;
import com.bc.app.server.mapper.FabricQcRecordProblemMapper;
import com.bc.app.server.service.FabricQcRecordProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 面料验货问题配置
 */
@Service("fabricQcRecordProblemService")
public class FabricQcRecordProblemServiceImpl implements FabricQcRecordProblemService {


    @Autowired
    private FabricQcRecordProblemMapper fabricQcRecordProblemMapper;

    /**
     * 新增问题配置
     *
     * @param fabricQcRecordProblem
     */
    @Transactional
    @Override
    public void addFabricQcRecordProblem(FabricQcRecordProblem fabricQcRecordProblem) {
        fabricQcRecordProblemMapper.addFabricQcRecordProblem(fabricQcRecordProblem);
    }

    /**
     * 通过验货表id查询问题集合
     *
     * @param fabricQcRecordProblem
     */
    @Transactional
    @Override
    public List<FabricQcRecordProblem> getFabricQcRecordProblemByRecordId(FabricQcRecordProblem fabricQcRecordProblem) {
        return fabricQcRecordProblemMapper.getFabricQcRecordProblemByRecordId(fabricQcRecordProblem);
    }

    /**
     * 删除数据
     *
     * @param fabricQcRecordProblem
     */
    @Override
    public Integer delete(FabricQcRecordProblem fabricQcRecordProblem) {
        return fabricQcRecordProblemMapper.delete(fabricQcRecordProblem);
    }

    /**
     * 通过id更新数据
     *
     * @param fabricQcRecordProblem
     */
    @Override
    public void updateById(FabricQcRecordProblem fabricQcRecordProblem) {
        fabricQcRecordProblemMapper.updateById(fabricQcRecordProblem);
    }

    /**
     * 通过id查询数据
     *
     * @param fabricQcRecordProblem
     * @return
     */
    @Override
    public FabricQcRecordProblem selectById(FabricQcRecordProblem fabricQcRecordProblem) {
        return fabricQcRecordProblemMapper.selectById(fabricQcRecordProblem);
    }

    /**
     * 通过验货表id查询统计列表
     *
     * @param fabricQcRecordProblem
     * @return
     */
    @Override
    public List<FabricQcRecordProblem> getCountData(FabricQcRecordProblem fabricQcRecordProblem) {
        return fabricQcRecordProblemMapper.getCountData(fabricQcRecordProblem);
    }
}
