package com.bc.app.server.entity;

import com.bc.app.server.utils.CommonUtil;

/**
 * 用户申请
 *
 * @author zhou
 */
public class UserApply {

    private String id;
    private String enterpriseId;
    private String name;
    private String phone;
    private String password;

    public UserApply() {

    }

    public UserApply(String enterpriseId, String name, String phone, String password) {
        this.id = CommonUtil.generateId();
        this.enterpriseId = enterpriseId;
        this.name = name;
        this.phone = phone;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
