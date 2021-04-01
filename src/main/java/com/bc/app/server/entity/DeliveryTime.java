package com.bc.app.server.entity;

/**
 * 交期
 *
 * @author zhou
 */
public class DeliveryTime {

    private String deliveryCount;
    private String deliveryTime;
    private String remark;

    public String getDeliveryCount() {
        return deliveryCount;
    }

    public void setDeliveryCount(String deliveryCount) {
        this.deliveryCount = deliveryCount;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
