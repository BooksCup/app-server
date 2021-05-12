package com.bc.app.server.mapper;

import com.bc.app.server.entity.SystemConfig;

/**
 * 系统配置
 *
 * @author zhou
 */
public interface SystemConfigMapper {

    /**
     * 获取系统配置
     *
     * @param key 配置key
     * @return 系统配置
     */
    SystemConfig getSystemConfigByConfigKey(String key);

}
