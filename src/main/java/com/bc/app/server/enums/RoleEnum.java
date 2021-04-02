package com.bc.app.server.enums;

/**
 * 角色
 *
 * @author zhou
 */
public enum RoleEnum {

    /**
     * 角色
     */
    ADMIN("0", "管理员"),
    AUDITOR("1", "审核人"),
    COPY("2", "抄送人"),
    ;

    RoleEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
