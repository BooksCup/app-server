package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author whl
 */
@Data
public class StockApplicationOrder implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 规格ID
     */
    private String specId;

    /**
     * 规格单价
     */
    private String specPrice;

    /**
     * 物品ID
     */
    private String goodsId;

    /**
     * 出入库数量
     */
    private String stockNumber;

    /**
     * 分配数量
     */
    private String giveNumber;

    /**
     * 剩余数量
     */
    private String surplusNumber;

    /**
     * 出入库ID
     */
    private String stockApplicationId;

    /**
     * 创建者ID
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 币种
     */
    private String currency;

    /**
     * 排序
     */
    private int sort;

    /**
     * 仓库ID
     */
    private String wareHouseId;
    /**
     * 箱子ID
     */
    private String boxId;
    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 出入库记录ID
     */
    private String recordId;

    /**
     * 入库1 出库2
     */
    private String stockType;
}
