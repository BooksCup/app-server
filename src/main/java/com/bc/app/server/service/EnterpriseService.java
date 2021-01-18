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

    /**
     * 修改企业
     *
     * @param enterprise 企业
     */
    void updateEnterprise(Enterprise enterprise);

    /**
     * 根据企业ID获取企业
     *
     * @param enterpriseId 企业ID
     * @return 企业
     */
    Enterprise getEnterpriseById(String enterpriseId);
}
