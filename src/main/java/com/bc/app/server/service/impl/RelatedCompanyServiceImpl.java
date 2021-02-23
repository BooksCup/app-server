package com.bc.app.server.service.impl;

import com.bc.app.server.entity.RelatedCompany;
import com.bc.app.server.mapper.RelatedCompanyMapper;
import com.bc.app.server.service.RelatedCompanyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 往来单位
 *
 * @author qiu
 */
@Service("exchangeEnterpriseService")
public class RelatedCompanyServiceImpl implements RelatedCompanyService {

    @Autowired
    RelatedCompanyMapper relatedCompanyMapper;

    /**
     * 获取往来单位信息
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 往来单位信息
     */
    @Override
    public PageInfo<RelatedCompany> getRelatedCompanyPageInfo(String enterpriseId, String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<RelatedCompany> page = relatedCompanyMapper.getRelatedCompanyPageInfo(enterpriseId, keyword);
        return new PageInfo<>(page);
    }

}
