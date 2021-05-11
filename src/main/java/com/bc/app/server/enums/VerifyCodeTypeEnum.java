package com.bc.app.server.enums;

/**
 * 验证码类别
 *
 * @author zhou
 */
public enum VerifyCodeTypeEnum {

    /**
     * 验证码类别
     */
    REGISTER("0", "注册"),
    MODIFY_PASSWORD("1", "修改登录密码"),
    ;

    VerifyCodeTypeEnum(String code, String name) {
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
