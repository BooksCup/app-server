package com.bc.app.server.mapper;

import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.vo.fabriccheckrecordproblemcontrollervo.FabricCheckRecordProblemVo;

import java.util.List;


/**
 * 记录问题
 */
public interface FabricCheckRecordProblemMapper {

    /**
     * 新增问题记录
     *
     * @param fabricCheckRecordProblemList
     */
    void addFabricQcRecordProblem(List<FabricCheckRecordProblem> fabricCheckRecordProblemList);


    /**
     * 通过验货表id查询问题集合
     *
     * @param fabricCheckRecordProblem
     */
    List<FabricCheckRecordProblem> getFabricQcRecordProblemByRecordId(FabricCheckRecordProblem fabricCheckRecordProblem);


    /**
     * 删除数据
     *
     * @param list id集合
     */
    Integer updateByIds(List<FabricCheckRecordProblem> list);

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

    List<FabricCheckRecordProblem> getCountData(FabricCheckRecordProblem fabricCheckRecordProblem);

    List<FabricCheckRecordProblemVo> getEntiryGroupByProblemPosition(FabricCheckRecordProblem fabricCheckRecordProblem);

    FabricCheckRecordProblem getEntiryByRecordRdAndPosition(FabricCheckRecordProblem fabricCheckRecordProblem);

    void updateByRecordId(FabricCheckRecordProblem fabricCheckRecordProblem);

    void deleteByRecordId(FabricCheckRecordProblem fabricCheckRecordProblem);
}
