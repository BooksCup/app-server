package com.bc.app.server.mapper;

import com.bc.app.server.entity.Enterprise;
import com.bc.app.server.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 企业
 *
 * @author zhou
 */
public interface EnterpriseMapper {

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
     * 修改企业扩展
     *
     * @param enterprise 企业
     */
    void updateEnterpriseExt(Enterprise enterprise);

    /**
     * 根据企业ID获取企业
     *
     * @param enterpriseId 企业ID
     * @return 企业
     */
    Enterprise getEnterpriseById(String enterpriseId);

    /**
     * 获取企业下的审核人员列表
     *
     * @param paramMap 参数map
     * @return 企业下的审核人员列表
     */
    List<User> getEnterpriseUserList(Map<String, Object> paramMap);

}
