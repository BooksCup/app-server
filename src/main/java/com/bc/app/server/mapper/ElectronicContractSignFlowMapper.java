package com.bc.app.server.mapper;

import com.bc.app.server.entity.econtract.SignFlow;

/**
 * 电子合同签署流程
 *
 * @author zhou
 */
public interface ElectronicContractSignFlowMapper {

    /**
     * 新增电子合同签署流程
     *
     * @param signFlow 签署流程
     */
    void addElectronicContractSignFlow(SignFlow signFlow);

}
