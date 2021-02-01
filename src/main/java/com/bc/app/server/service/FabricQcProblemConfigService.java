package com.bc.app.server.service;

import com.bc.app.server.entity.FabricQcProblemConfig;
import com.bc.app.server.entity.UserApply;

import java.util.List;
import java.util.Map;

/**
 * 面料验货问题配置
 */
public interface FabricQcProblemConfigService {

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
