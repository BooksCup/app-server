package com.bc.app.server.service;


import com.bc.app.server.entity.RelatedCompany;
import com.github.pagehelper.PageInfo;


/**
 * 往来单位
 *
 * @author qiu
 */
public interface RelatedCompanyService {

    /**
     * 获取往来单位信息
     *
     * @param enterpriseId 企业id
     * @param searchKey    搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 往来单位信息
     */
    PageInfo<RelatedCompany> getRelatedCompanyPageInfo(String enterpriseId, String searchKey, Integer pageNum, Integer pageSize);

}
