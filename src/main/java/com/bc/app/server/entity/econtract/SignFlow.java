package com.bc.app.server.entity.econtract;

import com.bc.app.server.utils.CommonUtil;

/**
 * 签署流程
 *
 * @author zhou
 */
public class SignFlow {

    private String id;
    private String contractId;
    private String flowId;
    private String createTime;

    public SignFlow() {

    }

    public SignFlow(String contractId, String flowId) {
        this.id = CommonUtil.generateId();
        this.contractId = contractId;
        this.flowId = flowId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
