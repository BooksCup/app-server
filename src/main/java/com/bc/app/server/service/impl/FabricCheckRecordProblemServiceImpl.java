package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.mapper.FabricCheckRecordProblemMapper;
import com.bc.app.server.service.FabricCheckRecordProblemService;
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
     * @param fabricCheckRecordProblem
     */
    @Transactional
    @Override
    public void addFabricQcRecordProblem(FabricCheckRecordProblem fabricCheckRecordProblem) {
        fabricCheckRecordProblemMapper.addFabricQcRecordProblem(fabricCheckRecordProblem);
    }

    /**
     * 通过验货表id查询问题集合
     *
     * @param fabricCheckRecordProblem
     */
    @Transactional
    @Override
    public List<FabricCheckRecordProblem> getFabricQcRecordProblemByRecordId(FabricCheckRecordProblem fabricCheckRecordProblem) {
        return fabricCheckRecordProblemMapper.getFabricQcRecordProblemByRecordId(fabricCheckRecordProblem);
    }

    /**
     * 删除数据
     *
     * @param fabricCheckRecordProblem
     */
    @Override
    public Integer delete(FabricCheckRecordProblem fabricCheckRecordProblem) {
        return fabricCheckRecordProblemMapper.delete(fabricCheckRecordProblem);
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
