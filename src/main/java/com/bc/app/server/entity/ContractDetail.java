package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 合同明细
 *
 * @author leo
 */
@Data
public class ContractDetail implements Serializable {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 合同主键ID
     */
    private String contractId;

    /**
     * 来源数据的主键ID
     */
    private String sourceId;

    /**
     * 来源数据的序列化json
     */
    private String sourceJson;

    /**
     * 本次数量总和
     */
    private String thisCount;

    /**
     * 本次数量JSON
     */
    private String thisCountJson;

    /**
     * 排序
     */
    private int sort;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改时间
     */
    private String modifyTime;

    private String display;
}
