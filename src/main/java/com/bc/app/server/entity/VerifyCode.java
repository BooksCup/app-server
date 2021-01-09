package com.bc.app.server.entity;

import com.bc.app.server.utils.CommonUtil;

/**
 * 验证码
 *
 * @author zhou
 */
public class VerifyCode {

    private String id;
    private String phone;
    private String code;
    private String type;
    private String createTime;
    private String expireTime;

    public VerifyCode() {

    }

    public VerifyCode(String phone, String code, String type, long period) {
        this.id = CommonUtil.generateId();
        this.phone = phone;
        this.code = code;
        this.type = type;
        this.createTime = CommonUtil.now();
        this.expireTime = CommonUtil.getExpireTime(period);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

}
