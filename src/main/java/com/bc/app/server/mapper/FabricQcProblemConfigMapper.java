package com.bc.app.server.mapper;

import com.bc.app.server.entity.FabricQcProblemConfig;

import java.util.List;


/**
 * 面料验货问题配置
 */
public interface FabricQcProblemConfigMapper {

    /**
     * 新增问题配置
     *
     * @param fabricQcProblemConfig
     */
    void addFabricQcProblemConfig(FabricQcProblemConfig fabricQcProblemConfig);

    /**
     * 通过企业id查询问题配置
     *
     * @param fabricQcProblemConfig
     */
    List<FabricQcProblemConfig> getAlllByEnterpriseId(FabricQcProblemConfig fabricQcProblemConfig);

}
