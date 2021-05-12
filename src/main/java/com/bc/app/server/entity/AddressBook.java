package com.bc.app.server.entity;

import com.bc.app.server.utils.CommonUtil;

/**
 * 通讯录
 *
 * @author zhou
 */
public class AddressBook {

    private String id;
    private String userId;
    private String type;
    private String name;
    private String phones;
    private String images;
    private String tels;
    private String email;
    private String company;
    private String job;
    private String website;
    private String address;
    private String tags;
    private String remark;

    public AddressBook() {

    }

    public AddressBook(String userId, String name, String images, String phones, String tels, String email,
                       String company, String job, String website, String address, String tags, String remark) {
        this.id = CommonUtil.generateId();
        this.userId = userId;
        this.name = name;
        this.images = images;
        this.phones = phones;
        this.tels = tels;
        this.email = email;
        this.company = company;
        this.job = job;
        this.website = website;
        this.address = address;
        this.tags = tags;
        this.remark = remark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getTels() {
        return tels;
    }

    public void setTels(String tels) {
        this.tels = tels;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}