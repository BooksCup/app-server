package com.bc.app.server.service.impl;

import com.bc.app.server.entity.econtract.Linkman;
import com.bc.app.server.mapper.LinkmanMapper;
import com.bc.app.server.service.LinkmanService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
     * 获取合同联系人分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 合同联系人分页信息
     */
    @Override
    public PageInfo<Linkman> getLinkmanPageInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Linkman> linkmanList = linkmanMapper.getLinkmanList(paramMap);
        return new PageInfo<>(linkmanList);
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
