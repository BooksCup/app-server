package com.bc.app.server.service.impl;

import com.bc.app.server.entity.PushTemplate;
import com.bc.app.server.mapper.PushTemplateMapper;
import com.bc.app.server.service.PushTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 推送模板
 *
 * @author zhou
 */
@Service("pushTemplateService")
public class PushTemplateServiceImpl implements PushTemplateService {

    @Resource
    PushTemplateMapper pushTemplateMapper;

    /**
     * 根据推送类型获取推送模板
     *
     * @param serviceType 推送类型
     * @return 推送模板
     */
    @Override
    public PushTemplate getPushTemplateMapperByServiceType(String serviceType) {
        return pushTemplateMapper.getPushTemplateMapperByServiceType(serviceType);
    }

}
