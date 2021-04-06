package com.bc.app.server.service;

import com.bc.app.server.entity.econtract.ContractFlow;
import org.springframework.http.ResponseEntity;

/**
 * 电子合同
 *
 * @author zhou
 */
public interface ElectronicContractService {

    /**
     * 创建并启动签署流程
     *
     * @param contractId   合同ID
     * @param contractFlow 签署流程
     * @return ResponseEntity
     */
    ResponseEntity<String> createAndStartSignFlow(String contractId, ContractFlow contractFlow);

}
