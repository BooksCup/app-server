package com.bc.app.server.entity.econtract;

/**
 * 签署结果
 *
 * @author zhou
 */
public class SignResult {

    private String action;
    private String flowId;
    private String accountId;
    private String authorizedAccountId;
    private String thirdOrderNo;
    private Integer order;
    private String signTime;
    private Integer signResult;
    private String resultDescription;
    private Long timestamp;
    private String thirdPartyUserId;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAuthorizedAccountId() {
        return authorizedAccountId;
    }

    public void setAuthorizedAccountId(String authorizedAccountId) {
        this.authorizedAccountId = authorizedAccountId;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public void setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public Integer getSignResult() {
        return signResult;
    }

    public void setSignResult(Integer signResult) {
        this.signResult = signResult;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getThirdPartyUserId() {
        return thirdPartyUserId;
    }

    public void setThirdPartyUserId(String thirdPartyUserId) {
        this.thirdPartyUserId = thirdPartyUserId;
    }

    @Override
    public String toString() {
        return "SignResult{" +
                "action='" + action + '\'' +
                ", flowId='" + flowId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", authorizedAccountId='" + authorizedAccountId + '\'' +
                ", thirdOrderNo='" + thirdOrderNo + '\'' +
                ", order=" + order +
                ", signTime='" + signTime + '\'' +
                ", signResult=" + signResult +
                ", resultDescription='" + resultDescription + '\'' +
                ", timestamp=" + timestamp +
                ", thirdPartyUserId='" + thirdPartyUserId + '\'' +
                '}';
    }
}
