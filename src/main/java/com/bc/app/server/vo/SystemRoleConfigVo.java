package com.bc.app.server.vo;

import com.bc.app.server.entity.User;

import java.util.List;

/**
 * 系统角色配置VO
 *
 * @author zhou
 */
public class SystemRoleConfigVo {

    private List<User> adminList;
    private List<User> auditorList;
    private List<User> copyList;

    public List<User> getAdminList() {
        return adminList;
    }

    public void setAdminList(List<User> adminList) {
        this.adminList = adminList;
    }

    public List<User> getAuditorList() {
        return auditorList;
    }

    public void setAuditorList(List<User> auditorList) {
        this.auditorList = auditorList;
    }

    public List<User> getCopyList() {
        return copyList;
    }

    public void setCopyList(List<User> copyList) {
        this.copyList = copyList;
    }

}
