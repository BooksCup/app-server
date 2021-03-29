package com.bc.app.server.entity;

/**
 * 机构印章
 *
 * @author zhou
 */
public class OrgSeal {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 企业id
     */
    private String enterpriseId;

    /**
     * 平台用户id
     */
    private String orgId;

    /**
     * 状态 0:正常 1:删除
     */
    private String status;

    /**
     * 默认状态 0:非默认 1:默认
     */
    private String defaultFlag;

    /**
     * 平台印章id
     */
    private String sealId;

    /**
     * 印章别名
     */
    private String alias;

    /**
     * 印章颜色
     */
    private String color;

    /**
     * 印章高度
     */
    private Integer height;

    /**
     * 印章宽度
     */
    private Integer width;

    /**
     * 印章横向文
     */
    private String htext;

    /**
     * 印章下弦文
     */
    private String qtext;

    /**
     * 印章类型
     */
    private String type;

    /**
     * 印章中心图案类型
     */
    private String central;

    /**
     * 图片地址
     */
    private String url;

    /**
     * 印章模板类型
     */
    private String templateType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(String defaultFlag) {
        this.defaultFlag = defaultFlag;
    }

    public String getSealId() {
        return sealId;
    }

    public void setSealId(String sealId) {
        this.sealId = sealId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getHtext() {
        return htext;
    }

    public void setHtext(String htext) {
        this.htext = htext;
    }

    public String getQtext() {
        return qtext;
    }

    public void setQtext(String qtext) {
        this.qtext = qtext;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCentral() {
        return central;
    }

    public void setCentral(String central) {
        this.central = central;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }
}
