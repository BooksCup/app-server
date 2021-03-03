package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: wd-saas
 * @description:出入库箱子记录
 * @author: Mr.Wang
 * @create: 2019-09-17 14:29
 **/
@Data
public class StockApplicationPackageRecord implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 箱子ID
     */
    private String boxId;

    /**
     * 排序号
     */
    private int sort;

    /**
     * 物品ID
     */
    private String goodsId;

    /**
     * 规格ID
     */
    private String goodsSpecId;

    /**
     * 入库数量
     */
    private String count;

    /**
     * 申请数量 == 出入库数量
     */
    private String applyNumber;

    /**
     * 出入库主键
     */
    private String stockApplicationId;

    /**
     * 出入库价格
     */
    private String price;

    /**
     * 币种
     */
    private String currency;

    /**
     * 创建者
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 企业主键
     */
    private String enterpriseId;

    /**
     * 规格x
     */
    private String x;

    /**
     * 规格Y
     */
    private String y;
}
