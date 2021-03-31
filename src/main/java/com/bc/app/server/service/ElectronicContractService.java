package com.bc.app.server.service;

import com.bc.app.server.entity.ContractSignFlow;
import com.bc.app.server.entity.econtract.ContractFlow;
import org.springframework.http.ResponseEntity;

/**
 * 电子合同
 *
 * @author zhou
 */
public interface ElectronicContractService {

    ResponseEntity<String> createSignFlow(ContractSignFlow signFlow);

    ResponseEntity<String> createSignFlow2(ContractFlow contractFlow);

}
