package com.bc.app.server.entity;

import com.bc.app.server.utils.CommonUtil;

/**
 * 系统配置
 *
 * @author zhou
 */
public class SystemConfig {

    private String id;
    private String key;
    private String value;

    public SystemConfig() {

    }

    public SystemConfig(String key, String value) {
        this.id = CommonUtil.generateId();
        this.key = key;
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
