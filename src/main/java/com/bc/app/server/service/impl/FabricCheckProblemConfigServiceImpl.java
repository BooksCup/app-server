package com.bc.app.server.service.impl;

import com.bc.app.server.entity.FabricCheckProblemConfig;
import com.bc.app.server.mapper.FabricCheckProblemConfigMapper;
import com.bc.app.server.service.FabricCheckProblemConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 面料验货问题配置
 */
@Service("fabricQcProblemConfigService")
public class FabricCheckProblemConfigServiceImpl implements FabricCheckProblemConfigService {


    @Autowired
    private FabricCheckProblemConfigMapper fabricCheckProblemConfigMapper;

    /**
     * 新增问题配置
     *
     * @param fabricCheckProblemConfig
     */
    @Transactional
    @Override
    public void addFabricQcProblemConfig(FabricCheckProblemConfig fabricCheckProblemConfig) {
        fabricCheckProblemConfigMapper.addFabricQcProblemConfig(fabricCheckProblemConfig);
    }

    /**
     * 通过企业id查询问题配置
     *
     * @param fabricCheckProblemConfig
     * @return
     */
    @Override
    public List<FabricCheckProblemConfig> getAlllByEnterpriseId(FabricCheckProblemConfig fabricCheckProblemConfig) {
        return fabricCheckProblemConfigMapper.getAlllByEnterpriseId(fabricCheckProblemConfig);
    }
}
