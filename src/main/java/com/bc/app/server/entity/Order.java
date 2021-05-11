package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 订单实体
 *
 * @author leo
 */
@Data
public class Order implements Serializable {
    private String goodsName;
    private String modifyTime;
    private String goodsNo;
    private String goodsPhotos;
    private String relatedCompanyName;
    private String relatedCompanyShortName;
    private String deliveryDates;
    private String orderId;
    private String extId;
    private String orderNo;
    private String themeId;
    private String orderTheme;
    private String relatedCompanyId;
    private String goodsId;
    private String num;
    private String applyCount;
    private String fromUserId;
    private String fromEnterpriseId;
    private String type;
    private String remarks;
    private String orderPhotos;
    private String goodsTypeId;
    private String goodsTypeName;
}
