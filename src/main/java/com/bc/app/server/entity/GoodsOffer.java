package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author
 * 报价
 */
@Data
public class GoodsOffer implements Serializable {


    /**
     * 报价ID
     */
    private String goodsOfferId;

    /**
     * 订单报价ID
     */
    private String quoteId;

    /**
     * 物品ID
     */
    private String goodsId;

    /**
     * 物料号
     */
    private String goodsNo;

    /**
     * 往来物料号（貌似已废弃）
     */
    private String tradeNo;

    /**
     * 往来企业
     */
    private String intercourseEnterprise;

    /**
     * 往来物料号
     */
    private String unitNo;

    /**
     * 0代表报进 1代表报出
     */
    private String offerType;

    /**
     * 报价
     */
    private String offerPrice;

    /**
     * 报价时间
     */
    private String offerTime;

    /**
     * 报价经办人
     */
    private String offerAgent;

    /**
     * 报价备注
     */
    private String offerRemark;

    /**
     * 报价修改时间
     */
    private String modifyTime;

    /**
     * 类型 0代表物品里的报价 1代表业务里的报价
     */
    private int type;

    /**
     * 币种
     */
    private String currency;

    /**
     * 0未作废1已作废
     */
    private String deleteStatus;

    /**
     * 企业主键
     */
    private String enterpriseId;

}
