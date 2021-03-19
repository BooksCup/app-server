package com.bc.app.server.service;

import com.bc.app.server.entity.PushTemplate;

/**
 * 推送模板
 *
 * @author zhou
 */
public interface PushTemplateService {

    /**
     * 根据推送类型获取推送模板
     *
     * @param serviceType 推送类型
     * @return 推送模板
     */
    PushTemplate getPushTemplateMapperByServiceType(String serviceType);

}
