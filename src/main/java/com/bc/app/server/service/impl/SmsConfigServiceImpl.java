package com.bc.app.server.service.impl;

import com.bc.app.server.entity.SmsConfig;
import com.bc.app.server.mapper.SmsConfigMapper;
import com.bc.app.server.service.SmsConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 短信配置
 *
 * @author zhou
 */
@Service("smsConfigService")
public class SmsConfigServiceImpl implements SmsConfigService {

    @Resource
    private SmsConfigMapper smsConfigMapper;

    /**
     * 根据类型获取短信配置
     *
     * @param type 类型
     * @return 短信配置
     */
    @Override
    public SmsConfig getSmsConfigByType(String type) {
        return smsConfigMapper.getSmsConfigByType(type);
    }

}
