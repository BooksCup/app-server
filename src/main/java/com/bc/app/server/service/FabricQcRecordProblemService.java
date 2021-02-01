package com.bc.app.server.service;

import com.bc.app.server.entity.FabricQcRecordProblem;

import java.util.List;


/**
 * 面料验货问题记录
 */
public interface FabricQcRecordProblemService {

    /**
     * 新增问题记录
     *
     * @param fabricQcRecordProblem
     */
    void addFabricQcRecordProblem(FabricQcRecordProblem fabricQcRecordProblem);

    /**
     * 通过验货表id查询问题集合
     *
     * @param fabricQcRecordProblem
     */
    List<FabricQcRecordProblem> getFabricQcRecordProblemByRecordId(FabricQcRecordProblem fabricQcRecordProblem);

    /**
     * 删除数据
     *
     * @param fabricQcRecordProblem
     */
    Integer delete(FabricQcRecordProblem fabricQcRecordProblem);

    /**
     * 通过id更新数据
     *
     * @param fabricQcRecordProblem
     */
    void updateById(FabricQcRecordProblem fabricQcRecordProblem);

    /**
     * 通过id查询数据
     *
     * @param fabricQcRecordProblem
     * @return
     */
    FabricQcRecordProblem selectById(FabricQcRecordProblem fabricQcRecordProblem);

    /**
     * 通过验货表id查询统计列表
     *
     * @param fabricQcRecordProblem
     * @return
     */
    List<FabricQcRecordProblem> getCountData(FabricQcRecordProblem fabricQcRecordProblem);
}
