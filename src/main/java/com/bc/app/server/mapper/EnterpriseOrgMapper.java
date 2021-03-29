package com.bc.app.server.mapper;

import com.bc.app.server.entity.EnterpriseOrg;

/**
 * 电子合同企业账号
 *
 * @author zhou
 */
public interface EnterpriseOrgMapper {

    /**
     * 新增企业账号
     *
     * @param enterpriseOrg 企业账号
     */
    void addEnterpriseOrg(EnterpriseOrg enterpriseOrg);

    /**
     * 修改企业账号
     *
     * @param enterpriseOrg 企业账号
     */
    void updateEnterpriseOrg(EnterpriseOrg enterpriseOrg);

    /**
     * 根据企业ID查询企业账号
     *
     * @param enterpriseId 企业ID
     * @return 企业账号
     */
    EnterpriseOrg getEnterpriseOrg(String enterpriseId);

}
