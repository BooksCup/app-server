package com.bc.app.server.mapper;

import com.bc.app.server.entity.OrgSeal;

import java.util.List;
import java.util.Map;

/**
 * 机构印章
 *
 * @author zhou
 */
public interface OrgSealMapper {

    /**
     * 保存机构印章
     *
     * @param orgSeal 机构印章
     */
    void addOrgSeal(OrgSeal orgSeal);

    /**
     * 保存机构印章列表
     *
     * @param orgSealList 机构印章列表
     */
    void addOrgSealList(List<OrgSeal> orgSealList);

    /**
     * 获取企业下的机构印章列表
     *
     * @param enterpriseId 企业ID
     * @return 机构印章列表
     */
    List<OrgSeal> getOrgSealListByEnterpriseId(String enterpriseId);


    /**
     * 根据第三方平台印章ID获取机构印章
     *
     * @param sealId 第三方平台印章ID
     * @return 机构印章
     */
    OrgSeal getOrgSealBySealId(String sealId);

    /**
     * 通过机构印章ID获取机构印章
     *
     * @param id 机构印章ID
     * @return 机构印章
     */
    OrgSeal getOrgSealById(String id);

    /**
     * 根据机构印章ID设置或取消默认印章
     *
     * @param paramMap 参数map
     */
    void updateOrgSealDefault(Map<String, Object> paramMap);

    /**
     * 修改印章别名
     *
     * @param paramMap 参数map
     */
    void updateOrgSealAlias(Map<String, String> paramMap);

    /**
     * 删除机构印章
     *
     * @param id 机构印章ID
     */
    void deleteOrgSeal(String id);
}
