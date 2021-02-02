package com.bc.app.server.entity;

import com.bc.app.server.utils.CommonUtil;

/**
 * 用户应用程序
 *
 * @author zhou
 */
public class UserApp {

    private String id;
    private String userId;
    private String appId;

    public UserApp() {

    }

    public UserApp(String userId, String appId) {
        this.id = CommonUtil.generateId();
        this.userId = userId;
        this.appId = appId;
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

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

}
