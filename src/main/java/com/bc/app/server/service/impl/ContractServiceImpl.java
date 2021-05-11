package com.bc.app.server.service.impl;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.Contract;
import com.bc.app.server.entity.ContractDetail;
import com.bc.app.server.entity.OrgSeal;
import com.bc.app.server.mapper.ContractMapper;
import com.bc.app.server.mapper.OrgSealMapper;
import com.bc.app.server.service.ContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
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

    @Resource
    ContractMapper contractMapper;

    @Resource
    OrgSealMapper orgSealMapper;

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
    @Override
    public PageInfo<Contract> getContractPageInfo(String enterpriseId, String phone, String keyword, Integer pageNum, Integer pageSize) {
        Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        paramMap.put("keyword", keyword);
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("phone", phone);
        PageHelper.startPage(pageNum, pageSize);
        List<Contract> page = contractMapper.getContractList(paramMap);
        return new PageInfo<>(page);
    }

    /**
     * 根据合同ID获取合同
     *
     * @param contractId   合同ID
     * @param enterpriseId 企业ID
     * @return 合同
     */
    @Override
    public Contract getContractById(String contractId, String enterpriseId) {
        Contract contract = contractMapper.getContractById(contractId);
        if (null != contract) {
            List<ContractDetail> contractDetailList = contractMapper.getContractDetailListByContractId(contractId);
            contract.setContractDetailList(contractDetailList);

            if (!StringUtils.isEmpty(enterpriseId)) {
                List<OrgSeal> orgSealList = orgSealMapper.getOrgSealListByEnterpriseId(enterpriseId);
                contract.setOrgSealList(orgSealList);
            }

        }
        return contract;
    }

    /**
     * 修改合同发送状态
     *
     * @param paramMap 参数map
     */
    @Override
    public void updateContractSendStatus(Map<String, Object> paramMap) {
        contractMapper.updateContractSendStatus(paramMap);
    }

}
