package com.bc.app.server.mapper;

import com.bc.app.server.entity.Contract;
import com.bc.app.server.entity.ContractDetail;

import java.util.List;
import java.util.Map;

/**
 * 合同
 *
 * @author zhou
 */
public interface ContractMapper {

    /**
     * 获取合同列表
     *
     * @param map 参数map
     * @return 合同列表
     */
    List<Contract> getContractList(Map<String, String> map);

    /**
     * 根据合同ID获取合同
     *
     * @param contractId 合同ID
     * @return 合同
     */
    Contract getContractById(String contractId);

    /**
     * 根据合同ID获取合同详情列表
     *
     * @param contractId 合同ID
     * @return 合同详情列表
     */
    List<ContractDetail> getContractDetailListByContractId(String contractId);

}