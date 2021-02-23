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
    private String goodsNo;
    private String goodsPhotos;
    private String relatedCompanyName;
    private String relatedCompanyShortName;
    private String deliveryDates;
    private String orderId;
    private String orderNo;
    private String themeId;
    private String orderTheme;
    private String relatedCompanyId;
    private String goodsId;
    private String num;
}
