package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author whl
 */
@Data
public class GoodsSpec implements Serializable {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 创建者用户ID
     */
    private String useId;

    /**
     * 物品主键ID
     */
    private String goodsId;

    /**
     * 规格1的值
     */
    private String x;

    /**
     * 规格2的值
     */
    private String y;

    /**
     * 是否显示
     */
    private String display;

    /**
     * 排序
     */
    private int sort;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 企业ID
     */
    private String enterpriseId;
}
