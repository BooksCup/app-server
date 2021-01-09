package com.bc.app.server.mapper;

import com.bc.app.server.entity.SmsConfig;

/**
 * 短信配置
 *
 * @author zhou
 */
public interface SmsConfigMapper {

    /**
     * 根据类型获取短信配置
     *
     * @param type 类型
     * @return 短信配置
     */
    SmsConfig getSmsConfigByType(String type);

}
