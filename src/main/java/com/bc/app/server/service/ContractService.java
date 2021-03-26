package com.bc.app.server.service;


import com.bc.app.server.entity.Contract;
import com.github.pagehelper.PageInfo;

/**
 * 合同
 *
 * @author zhou
 */
public interface ContractService {

    /**
     * 获取合同分页信息
     *
     * @param keyword      搜索关键字
     * @param enterpriseId 企业id
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 合同分页信息
     */
    PageInfo<Contract> getContractPageInfo(String enterpriseId, String keyword, Integer pageNum, Integer pageSize);

}
