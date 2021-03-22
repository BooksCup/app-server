package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 凭证数据与科目绑定实体类
 *
 * @author qiu
 */
@Data
public class FianceSubjectVoucher implements Serializable {


    private String id;

    /**
     * 凭证id
     */
    private String voucherId;

    /**
     * 科目code
     */
    private String subjectCode;

    /**
     * 目标
     */
    private String target;

    /**
     * 币种 1 美元汇率 2欧元汇率 3英镑 4港币 5加拿大元 6日元 7新台币 8越南币 9人民币
     */
    private Integer currency;

    /**
     * 金额
     */
    private String amount;

    /**
     * 备注
     */
    private String remark;


    /**
     * 用来判断是否加入到科目表中，如果类型少于循环数，则不加人
     */
    private Boolean joinsStatus;


    /**
     * 目类型  1 借  2贷
     */
    private String direction;


    //辅助字段

    //科目名称
    private String subjectName;


    //借方金额
    private String borrow;

    //贷方金额
    private String loan;

    //排序用的字段
    private String sort;


    //主体
    private String main;
}
