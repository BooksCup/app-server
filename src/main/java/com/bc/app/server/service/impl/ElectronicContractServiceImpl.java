package com.bc.app.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bc.app.server.entity.*;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.mapper.ContractMapper;
import com.bc.app.server.mapper.EnterpriseOrgMapper;
import com.bc.app.server.mapper.OrgSealMapper;
import com.bc.app.server.utils.ElectronicContractHttpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

/**
 * 电子合同
 *
 * @author zhou
 */
public class ElectronicContractServiceImpl {

    @Resource
    EnterpriseOrgMapper enterpriseOrgMapper;

    @Resource
    OrgSealMapper orgSealMapper;

    @Resource
    ContractMapper contractMapper;

    public ResponseEntity<String> createSignFlow(ContractSignFlow signFlow) {

        EnterpriseOrg enterpriseOrg = enterpriseOrgMapper.getEnterpriseOrg(signFlow.getEnterpriseId());
        if (!ObjectUtils.isEmpty(enterpriseOrg)) {
            if (!StringUtils.isEmpty(signFlow.getSealId())) {
                OrgSeal seal = orgSealMapper.getOrgSealById(signFlow.getSealId());
                if (ObjectUtils.isEmpty(seal)) {
                    return new ResponseEntity<>(ResponseMsg.ORG_SEAL_NOT_EXISTS.getResponseCode(), HttpStatus.BAD_REQUEST);
                } else {
                    signFlow.setSealId(seal.getSealId());
                }
            }
            String url = "/v1/signflows";

            JSONObject paramJson = new JSONObject();
            paramJson.put("autoArchive", true);
            Contract contract = contractMapper.getContractById(signFlow.getContractId());
            if (!StringUtils.isEmpty(contract.getTitle())) {
                paramJson.put("businessScene", contract.getTitle());
            } else {
                return new ResponseEntity<>(ResponseMsg.CONTRACT_TITLE_EMPTY.getResponseCode(), HttpStatus.BAD_REQUEST);
            }
            paramJson.put("initiatorAuthorizedAccountId", enterpriseOrg.getOrgId());
            ElectronicContractApiResult apiResult = ElectronicContractHttpUtil.httpPost(url, paramJson);
            if (apiResult.getCode() == 0) {
                String flowId = (String) apiResult.getData().get("flowId");
//                orderSync.insertContractInfo(org.getOrgId(), process.getSealId(), process.getUserId(), flowId, process.getContractId(), process.getImgStr());
            }
        } else {
            return new ResponseEntity<>(ResponseMsg.ENTERPRISE_NOT_CERT.getResponseCode(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResponseMsg.CREATE_SIGN_FLOW_SUCCESS.getResponseCode(), HttpStatus.OK);
    }
}
