package com.bc.app.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.*;
import com.bc.app.server.entity.econtract.ContractFlow;
import com.bc.app.server.entity.econtract.SignFlow;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.enums.SendStatusEnum;
import com.bc.app.server.mapper.ContractMapper;
import com.bc.app.server.mapper.ElectronicContractSignFlowMapper;
import com.bc.app.server.service.ElectronicContractService;
import com.bc.app.server.utils.ElectronicContractHttpUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 电子合同
 *
 * @author zhou
 */
@Service("electronicContractService")
public class ElectronicContractServiceImpl implements ElectronicContractService {

    @Resource
    ElectronicContractSignFlowMapper electronicContractSignFlowMapper;

    @Resource
    ContractMapper contractMapper;

    /**
     * 创建并启动签署流程
     *
     * @param contractId   合同ID
     * @param contractFlow 签署流程
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity<String> createAndStartSignFlow(String contractId, ContractFlow contractFlow) {
        ResponseEntity<String> responseEntity;
        String url = Constant.E_CONTRACT_BASE_URL + "/api/v2/signflows/createFlowOneStep";
        try {
            String paramJson = JSON.toJSONString(contractFlow);
            ElectronicContractApiResult apiResult = ElectronicContractHttpUtil.httpPost(url, paramJson);
            if (apiResult.getCode() == 0) {
                // 启动签署流程
                String flowId = (String) apiResult.getData().get("flowId");
                url = Constant.E_CONTRACT_BASE_URL + "/v1/signflows/" + flowId + "/start";
                ElectronicContractHttpUtil.httpPut(url, String.valueOf(new JSONObject()));
                // 持久化

                SignFlow signFlow = new SignFlow(contractId, flowId);
                electronicContractSignFlowMapper.addElectronicContractSignFlow(signFlow);

                // 合同状态修改为已发送
                Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
                paramMap.put("id", contractId);
                paramMap.put("sendStatus", SendStatusEnum.SENTED.getCode());
                contractMapper.updateContractSendStatus(paramMap);

            }
            responseEntity = new ResponseEntity<>(ResponseMsg.CREATE_AND_START_SIGN_FLOW_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.CREATE_AND_START_SIGN_FLOW_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
