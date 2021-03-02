package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author whl
 */
@Data
public class StockApplicationInRecord implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 仓库ID
     */
    private String wareHouseId;

    /**
     * 物品ID
     */
    private String goodsId;

    /**
     * 物品规格ID
     */
    private String goodsSpecId;

    /**
     * 出入库数量
     */
    private String count;

    /**
     * 申请数量 目前和count一致
     */
    private String applyNumber;

    /**
     * 出入库ID
     */
    private String stockApplicationId;

    /**
     * 库存数量
     */
    private String residualNumber;

    /**
     * 物品规格的价格
     */
    private String price;

    /**
     * 创建者
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 箱子ID
     */
    private String boxId;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 排序
     */
    private int sort;

    /**
     * 币种
     */
    private String currency;
}
