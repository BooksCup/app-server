package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricQcProblemConfig;
import com.bc.app.server.mapper.FabricQcProblemConfigMapper;
import com.bc.app.server.service.FabricQcProblemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 面料验货问题配置
 */
@Service("fabricQcProblemConfigService")
public class FabricQcProblemConfigServiceImpl implements FabricQcProblemConfigService {


    @Autowired
    private FabricQcProblemConfigMapper fabricQcProblemConfigMapper;

    /**
     * 新增问题配置
     *
     * @param fabricQcProblemConfig
     */
    @Transactional
    @Override
    public void addFabricQcProblemConfig(FabricQcProblemConfig fabricQcProblemConfig) {
        fabricQcProblemConfigMapper.addFabricQcProblemConfig(fabricQcProblemConfig);
    }

    /**
     * 通过企业id查询问题配置
     *
     * @param fabricQcProblemConfig
     * @return
     */
    @Override
    public List<FabricQcProblemConfig> getAlllByEnterpriseId(FabricQcProblemConfig fabricQcProblemConfig) {
        return fabricQcProblemConfigMapper.getAlllByEnterpriseId(fabricQcProblemConfig);
    }
}
