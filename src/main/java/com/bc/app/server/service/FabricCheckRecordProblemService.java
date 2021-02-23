package com.bc.app.server.service;

import com.bc.app.server.entity.FabricCheckRecordProblem;

import java.util.List;


/**
 * 面料验货问题记录
 */
public interface FabricCheckRecordProblemService {

    /**
     * 新增问题记录
     *
     * @param fabricCheckRecordProblem
     */
    void addFabricQcRecordProblem(FabricCheckRecordProblem fabricCheckRecordProblem);

    /**
     * 通过验货表id查询问题集合
     *
     * @param fabricCheckRecordProblem
     */
    List<FabricCheckRecordProblem> getFabricQcRecordProblemByRecordId(FabricCheckRecordProblem fabricCheckRecordProblem);

    /**
     * 删除数据
     *
     * @param fabricCheckRecordProblem
     */
    Integer delete(FabricCheckRecordProblem fabricCheckRecordProblem);

    /**
     * 通过id更新数据
     *
     * @param fabricCheckRecordProblem
     */
    void updateById(FabricCheckRecordProblem fabricCheckRecordProblem);

    /**
     * 通过id查询数据
     *
     * @param fabricCheckRecordProblem
     * @return
     */
    FabricCheckRecordProblem selectById(FabricCheckRecordProblem fabricCheckRecordProblem);

    /**
     * 通过验货表id查询统计列表
     *
     * @param fabricCheckRecordProblem
     * @return
     */
    List<FabricCheckRecordProblem> getCountData(FabricCheckRecordProblem fabricCheckRecordProblem);
}
