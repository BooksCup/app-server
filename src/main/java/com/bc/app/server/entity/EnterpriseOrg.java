package com.bc.app.server.entity;

/**
 * 电子合同企业账号
 *
 * @author zhou
 */
public class EnterpriseOrg {

    /**
     * 主键id
     */
    private String id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 第三方返回机构ID
     */
    private String orgId;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 认证类型 0:未认证 1:已认证
     */
    private String realType;

    /**
     * 流程ID
     */
    private String flowId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getRealType() {
        return realType;
    }

    public void setRealType(String realType) {
        this.realType = realType;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

}
