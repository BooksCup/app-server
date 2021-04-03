package com.bc.app.server.entity.econtract;

import com.bc.app.server.utils.CommonUtil;

/**
 * 合同联系人
 *
 * @author zhou
 */
public class Linkman {

    private String id;
    private String userId;
    private String enterpriseId;
    private String name;
    private String phone;
    private String email;
    private String companyName;
    private String remark;
    private String createTime;
    private String modifyTime;

    public Linkman() {

    }

    public Linkman(String name, String phone,
                   String email, String companyName, String remark) {
        this.id = CommonUtil.generateId();
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.companyName = companyName;
        this.remark = remark;
    }

    public Linkman(String userId, String enterpriseId, String name, String phone,
                   String email, String companyName, String remark) {
        this.id = CommonUtil.generateId();
        this.userId = userId;
        this.enterpriseId = enterpriseId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.companyName = companyName;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(String enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

}
