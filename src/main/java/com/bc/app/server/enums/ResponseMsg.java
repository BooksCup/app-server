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

    RESET_USER_APP_SUCCESS("RESET_USER_APP_SUCCESS", "重置用户APP成功!"),
    RESET_USER_APP_ERROR("RESET_USER_APP_ERROR", "重置用户APP失败!"),

    OPERATE_USER_APPLY_SUCCESS("OPERATE_USER_APPLY_SUCCESS", "操作用户申请成功!"),
    OPERATE_USER_APPLY_ERROR("OPERATE_USER_APPLY_ERROR", "操作用户申请失败!"),

    UPDATE_ENTERPRISE_SUCCESS("UPDATE_ENTERPRISE_SUCCESS", "修改企业成功"),
    UPDATE_ENTERPRISE_ERROR("UPDATE_ENTERPRISE_ERROR", "修改企业失败"),
    ADD_SUCCESS("ADD_SUCCESS", "新增成功"),
    ADD_ERROR("ADD_ERROR", "新增失败"),
    ADD_REPEAT("ADD_REPEAT", "重复新增"),


    UPDATE_SUCCESS("UPDATE_SUCCESS", "修改成功"),
    UPDATE_ERROR("UPDATE_ERROR", "修改失败/没传信息"),

    DELETE_SUCCESS("DETELE_SUCCESS", "删除成功"),
    DELETE_ERROR("DETELE_ERROR", "删除失败"),

    IMPORT_SUCCESS("IMPORT_SUCCESS", "导入成功"),
    IMPORT_ERROR("IMPORT_ERROR", "导入失败"),

    USER_NOT_EXIST("USER_NOT_EXIST", "用户不存在"),

    PUSH_SUCCESS("PUSH_SUCCESS", "推送成功"),
    PUSH_ERROR("PUSH_ERROR", "推送失败"),

    EXPORT_PDF_SUCCESS("EXPORT_PDF_SUCCESS", "导出PDF成功"),
    EXPORT_PDF_ERROR("EXPORT_PDF_ERROR", "导出PDF失败"),

    CREATE_SIGN_FLOW_SUCCESS("CREATE_SIGN_FLOW_SUCCESS", "签署流程创建成功"),
    ORG_SEAL_NOT_EXISTS("ORG_SEAL_NOT_EXISTS", "机构印章不存在"),
    CONTRACT_TITLE_EMPTY("CONTRACT_TITLE_EMPTY", "该合同没有设置标题"),
    ENTERPRISE_NOT_CERT("ENTERPRISE_NOT_CERT", "未获取到企业认证信息，请先认证企业"),
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
