package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 编号规则实体
 *
 * @author qiu
 */
@Data
public class NumberSequence implements Serializable {

    /**
     * 类型：需求
     */
    public static final String TYPE_REQUIREMENT = "1";

    /**
     * 类型：合同
     */
    public static final String TYPE_CONTRACT = "2";

    /**
     * 类型：订单
     */
    public static final String TYPE_ORDER = "3";

    /**
     * 主键ID
     */
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 值
     */
    private int val;

    /**
     * 用户主键ID
     */
    private String userId;

    /**
     * 企业主键ID
     */
    private String enterpriseId;

}
