package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 商品表
 */
@Data
public class Goods implements Serializable {


    private String id;
    /**
     * 品名
     */
    private String goodsName;
    /**
     * 产品照片
     */
    private String goodsNhotos;
    /**
     * 货号
     */
    private String goodsNo;
    /**
     * 物品属性集合LIST的JSON格式
     */
    private String attrList;

}
