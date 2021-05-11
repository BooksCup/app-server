package com.bc.app.server.entity;

import com.bc.app.server.utils.CommonUtil;

/**
 * 日程
 *
 * @author zhou
 */
public class Schedule {

    private String id;
    private String title;
    private String userId;
    private String beginTime;
    private String endTime;
    private String address;
    private String remark;
    private String images;

    public Schedule() {

    }

    public Schedule(String title, String userId, String beginTime, String endTime,
                    String address, String remark, String images) {
        this.id = CommonUtil.generateId();
        this.title = title;
        this.userId = userId;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.address = address;
        this.remark = remark;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

}
