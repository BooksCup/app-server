package com.bc.app.server.service.impl;

import com.bc.app.server.entity.Enterprise;
import com.bc.app.server.mapper.EnterpriseMapper;
import com.bc.app.server.service.EnterpriseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 企业
 *
 * @author zhou
 */
@Service("enterpriseService")
public class EnterpriseServiceImpl implements EnterpriseService {

    @Resource
    private EnterpriseMapper enterpriseMapper;

    /**
     * 根据关键字搜索企业列表
     *
     * @param paramMap 参数map
     * @return 企业列表
     */
    @Override
    public List<Enterprise> getEnterpriseListByKeyword(Map<String, Object> paramMap) {
        return enterpriseMapper.getEnterpriseListByKeyword(paramMap);
    }

    /**
     * 修改企业
     *
     * @param enterprise 企业
     */
    @Override
    public void updateEnterprise(Enterprise enterprise) {
        enterpriseMapper.updateEnterprise(enterprise);
        enterpriseMapper.updateEnterpriseExt(enterprise);
    }

    /**
     * 根据企业ID获取企业
     *
     * @param enterpriseId 企业ID
     * @return 企业
     */
    @Override
    public Enterprise getEnterpriseById(String enterpriseId) {
        return enterpriseMapper.getEnterpriseById(enterpriseId);
    }

}
