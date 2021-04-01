package com.bc.app.server.entity;

import java.util.List;

/**
 * 合同物品
 *
 * @author zhou
 */
public class ContractGoods {
    private String goodsNo;
    private String goodsName;
    private String applyCount;
    private String goodsUnit;
    private String goodsPrice;
    private String amount;
    private List<DeliveryTime> deliveryTimeList;
    private ContractExt ext;

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(String applyCount) {
        this.applyCount = applyCount;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public List<DeliveryTime> getDeliveryTimeList() {
        return deliveryTimeList;
    }

    public void setDeliveryTimeList(List<DeliveryTime> deliveryTimeList) {
        this.deliveryTimeList = deliveryTimeList;
    }

    public ContractExt getExt() {
        return ext;
    }

    public void setExt(ContractExt ext) {
        this.ext = ext;
    }
}
