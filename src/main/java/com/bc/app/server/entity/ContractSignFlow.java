package com.bc.app.server.entity;

/**
 * 合同签署流程
 *
 * @author zhou
 */
public class ContractSignFlow {

    /**
     * 合同ID
     */
    private String contractId;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 印章ID
     */
    private String sealId;

    /**
     * 用户ID
     */
    private String userId;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
