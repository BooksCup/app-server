package com.bc.app.server.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 凭证查询对象
 *
 * @author qiu
 */
@Data
public class FinanceVoucher implements Serializable {

    private String id;

    /**
     * 关联的部门主键
     */
    private String departmentId;

    /**
     * 关联的部门名称
     */
    private String departmentName;

    /**
     * 关联的企业主键
     */
    private String enterpriseId;

    /**
     * 创建人id
     */
    private String creatorId;

    /**
     * 关联的经办人名称
     */
    private String creatorName;

    /**
     * 关联的任务主键
     */
    private String exchangeId;

    /**
     * 审核人主键
     */
    private String auditId;

    /**
     * 业务类型
     */
    private String voucherType;

    /**
     * 模板类型 0不是模板 1模板
     */
    private Integer voucherTemplateType;

    /**
     * 模块名称，用于匹配每个模块的凭证
     */
    private String moduleName;


    /**
     * 摘要（凭证简要内容）
     */
    private String summary;

    /**
     * 币种 1 美元汇率 2欧元汇率 3英镑 4港币 5加拿大元 6日元 7新台币 8越南币 9人民币
     */
    private Integer currency;

    /**
     * 原币种金额
     */
    private String currencyAmount;

    /**
     * 币种金额
     */
    private String sourceCurrencyAmount;

    /**
     * 凭证编号
     */
    private String voucherNumber;

    /**
     * 凭证备注
     */
    private String remark;

    /**
     * 凭证状态 0未生成凭证 （未启用） 1 已生成凭证（启用）
     */
    private Integer voucherStatus;

    /**
     * '0初始状态  1审核中  2成功  3失败
     */
    private Integer auditStatus;

    /**
     * 审核时间
     */
    private String auditTime;

    /**
     * 新凭证编号（根据日期生成）
     */
    private String voucherNumberNew;

    /**
     * 生成这条凭证的业务表id
     */
    private String taskId;

    /**
     * 前端会追操作使用的字段
     */
    private String timeStamp;
}
