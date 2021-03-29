package com.bc.app.server.service.impl;

import com.bc.app.server.entity.OrgSeal;
import com.bc.app.server.mapper.OrgSealMapper;
import com.bc.app.server.service.OrgSealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 机构印章
 *
 * @author zhou
 */
@Service("orgSealService")
public class OrgSealServiceImpl implements OrgSealService {

    @Resource
    private OrgSealMapper orgSealMapper;

    /**
     * 获取企业下的机构印章列表
     *
     * @param enterpriseId 企业ID
     * @return 机构印章列表
     */
    @Override
    public List<OrgSeal> getOrgSealListByEnterpriseId(String enterpriseId) {
        return orgSealMapper.getOrgSealListByEnterpriseId(enterpriseId);
    }

}
