package com.bc.app.server.mapper;

import com.bc.app.server.entity.FabricCheckProblemConfig;

import java.util.List;


/**
 * 面料验货问题配置
 */
public interface FabricCheckProblemConfigMapper {

    /**
     * 新增问题配置
     *
     * @param fabricCheckProblemConfig
     */
    void addFabricQcProblemConfig(FabricCheckProblemConfig fabricCheckProblemConfig);

    /**
     * 通过企业id查询问题配置
     *
     * @param fabricCheckProblemConfig
     */
    List<FabricCheckProblemConfig> getAlllByEnterpriseId(FabricCheckProblemConfig fabricCheckProblemConfig);

}