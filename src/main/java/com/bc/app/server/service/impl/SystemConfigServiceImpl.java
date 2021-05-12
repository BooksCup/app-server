package com.bc.app.server.service.impl;

import com.bc.app.server.entity.SystemConfig;
import com.bc.app.server.mapper.SystemConfigMapper;
import com.bc.app.server.service.SystemConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统配置
 *
 * @author zhou
 */
@Service("systemConfigService")
public class SystemConfigServiceImpl implements SystemConfigService {

    @Resource
    private SystemConfigMapper systemConfigMapper;

    /**
     * 获取系统配置
     *
     * @param key 配置key
     * @return 系统配置
     */
    @Override
    public SystemConfig getSystemConfigByConfigKey(String key) {
        return systemConfigMapper.getSystemConfigByConfigKey(key);
    }

}