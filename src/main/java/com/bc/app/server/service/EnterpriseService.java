package com.bc.app.server.service;

import com.bc.app.server.entity.Enterprise;

import java.util.List;
import java.util.Map;

/**
 * 企业
 *
 * @author zhou
 */
public interface EnterpriseService {

    /**
     * 根据关键字搜索企业列表
     *
     * @param paramMap 参数map
     * @return 企业列表
     */
    List<Enterprise> getEnterpriseListByKeyword(Map<String, Object> paramMap);

}
