package com.bc.app.server.service;

import com.bc.app.server.entity.OrgSeal;

import java.util.List;

/**
 * 机构印章
 *
 * @author zhou
 */
public interface OrgSealService {

    /**
     * 获取企业下的机构印章列表
     *
     * @param enterpriseId 企业ID
     * @return 机构印章列表
     */
    List<OrgSeal> getOrgSealListByEnterpriseId(String enterpriseId);

}
