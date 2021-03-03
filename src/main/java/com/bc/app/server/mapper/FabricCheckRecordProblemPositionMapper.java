package com.bc.app.server.mapper;


import com.bc.app.server.entity.FabricCheckRecordProblemPosition;

import java.util.List;

/**
 * 问题所在位置应用
 */
public interface FabricCheckRecordProblemPositionMapper {

    /**
     * 添加问题位置信息
     *
     * @param fabricCheckRecordProblemPosition
     */
    void addFabricCheckRecordProblemPosition(FabricCheckRecordProblemPosition fabricCheckRecordProblemPosition);

    /**
     * 通recordId获取问题位置集合
     *
     * @param recordId recordId
     * @return 问题位置集合
     */
    List<FabricCheckRecordProblemPosition> getEntiryGroupByProblemPosition(String recordId);

    /**
     * 通过id删除数据
     *
     * @param id 问题位置id
     */
    void deleteById(String id);
}
