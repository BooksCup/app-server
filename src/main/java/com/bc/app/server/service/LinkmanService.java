package com.bc.app.server.service;

import com.bc.app.server.entity.econtract.Linkman;

import java.util.List;
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
     * 获取合同联系人列表
     *
     * @param paramMap 参数map
     * @return 合同联系人列表
     */
    List<Linkman> getLinkmanList(Map<String, Object> paramMap);

    /**
     * 删除合同联系人
     *
     * @param id 合同联系人主键
     */
    void deleteLinkman(String id);

}
