package com.bc.app.server.mapper;

import com.bc.app.server.entity.RelatedCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 往来单位
 *
 * @author qiu
 */
public interface RelatedCompanyMapper {

    /**
     * 获取往来单位信息
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @return 往来单位信息
     */
    List<RelatedCompany> getRelatedCompanyPageInfo(@Param("enterpriseId") String enterpriseId, @Param("keyword") String keyword);

}
