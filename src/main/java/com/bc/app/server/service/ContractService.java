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
     * @param phone        接收合同手机号
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 合同分页信息
     */
    PageInfo<Contract> getContractPageInfo(String enterpriseId, String phone, String keyword, Integer pageNum, Integer pageSize);

    /**
     * 根据合同ID获取合同
     *
     * @param contractId   合同ID
     * @param enterpriseId 企业ID
     * @return 合同
     */
    Contract getContractById(String contractId, String enterpriseId);

}
