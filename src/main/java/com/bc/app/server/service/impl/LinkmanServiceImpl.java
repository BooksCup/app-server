package com.bc.app.server.service.impl;

import com.bc.app.server.entity.econtract.Linkman;
import com.bc.app.server.mapper.LinkmanMapper;
import com.bc.app.server.service.LinkmanService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 合同联系人
 *
 * @author zhou
 */
@Service("linkmanService")
public class LinkmanServiceImpl implements LinkmanService {

    @Resource
    LinkmanMapper linkmanMapper;

    /**
     * 新增合同联系人
     *
     * @param linkman 合同联系人
     */
    @Override
    public void addLinkman(Linkman linkman) {
        linkmanMapper.addLinkman(linkman);
    }

    /**
     * 修改合同联系人
     *
     * @param linkman 合同联系人
     */
    @Override
    public void updateLinkman(Linkman linkman) {

    }

    /**
     * 获取合同联系人列表
     *
     * @param paramMap 参数map
     * @return 合同联系人列表
     */
    @Override
    public List<Linkman> getLinkmanList(Map<String, Object> paramMap) {
        return null;
    }

    /**
     * 删除合同联系人
     *
     * @param id 合同联系人主键
     */
    @Override
    public void deleteLinkman(String id) {

    }
}
