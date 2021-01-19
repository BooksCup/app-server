package com.bc.app.server.enums;

/**
 * 返回消息
 *
 * @author zhou
 */
public enum ResponseMsg {

    /**
     * app-server接口返回信息
     */
    VERIFY_CODE_EXPIRE("VERIFY_CODE_EXPIRE", "验证码失效!"),

    GET_VERIFY_CODE_SUCCESS("GET_VERIFY_CODE_SUCCESS", "获取验证码成功!"),
    GET_VERIFY_CODE_ERROR("GET_VERIFY_CODE_ERROR", "获取验证码失败!"),

    ADD_USER_APPLY_SUCCESS("ADD_USER_APPLY_SUCCESS", "用户申请提交成功!"),
    ADD_USER_APPLY_ERROR("ADD_USER_APPLY_ERROR", "用户申请提交失败!"),

    OPERATE_USER_APPLY_SUCCESS("OPERATE_USER_APPLY_SUCCESS", "操作用户申请成功!"),
    OPERATE_USER_APPLY_ERROR("OPERATE_USER_APPLY_ERROR", "操作用户申请失败!"),

    UPDATE_ENTERPRISE_SUCCESS("UPDATE_ENTERPRISE_SUCCESS", "修改企业成功"),
    UPDATE_ENTERPRISE_ERROR("UPDATE_ENTERPRISE_ERROR", "修改企业失败"),
    ;

    ResponseMsg(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    private String responseCode;
    private String responseMessage;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

}
