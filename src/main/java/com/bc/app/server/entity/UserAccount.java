package com.bc.app.server.entity;

import lombok.Data;

/**
 * 电子合同账号
 *
 * @author qiu
 */
@Data
public class UserAccount {

    /**
     * 主键id
     */
    private String id;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 第三方返回Id
     */
    private String accountId;

    /**
     * 企业id
     */
    private String userId;

    private String idCard;

    /**
     * 实名状态 0:未实名  1:已实名
     */
    private String realType;

    private String mobile;

    private String flowId;
}
