package com.bc.app.server.service;

import com.bc.app.server.entity.econtract.Linkman;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 合同联系人
 *
 * @author zhou
 */
public interface LinkmanService {

    /**
     * 新增合同联系人
     *
     * @param linkman 合同联系人
     */
    void addLinkman(Linkman linkman);

    /**
     * 修改合同联系人
     *
     * @param linkman 合同联系人
     */
    void updateLinkman(Linkman linkman);

    /**
     * 获取合同联系人分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 合同联系人分页信息
     */
    PageInfo<Linkman> getLinkmanPageInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    /**
     * 删除合同联系人
     *
     * @param id 合同联系人主键
     */
    void deleteLinkman(String id);

}
