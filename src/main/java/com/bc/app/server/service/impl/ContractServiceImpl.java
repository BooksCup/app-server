package com.bc.app.server.service.impl;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.Contract;
import com.bc.app.server.entity.ContractDetail;
import com.bc.app.server.mapper.ContractMapper;
import com.bc.app.server.service.ContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 合同
 *
 * @author zhou
 */
@Service("contractService")
public class ContractServiceImpl implements ContractService {

    @Autowired
    ContractMapper contractMapper;

    /**
     * 获取合同分页信息
     *
     * @param keyword      搜索关键字
     * @param enterpriseId 企业id
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 合同分页信息
     */
    @Override
    public PageInfo<Contract> getContractPageInfo(String enterpriseId, String keyword, Integer pageNum, Integer pageSize) {
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("keyword", keyword);
        map.put("enterpriseId", enterpriseId);
        PageHelper.startPage(pageNum, pageSize);
        List<Contract> page = contractMapper.getContractList(map);
        return new PageInfo<>(page);
    }

    /**
     * 根据合同ID获取合同
     *
     * @param contractId 合同ID
     * @return 合同
     */
    @Override
    public Contract getContractById(String contractId) {
        Contract contract = contractMapper.getContractById(contractId);
        if (null != contract) {
            List<ContractDetail> contractDetailList = contractMapper.getContractDetailListByContractId(contractId);
            contract.setContractDetailList(contractDetailList);
        }
        return contract;
    }

}
