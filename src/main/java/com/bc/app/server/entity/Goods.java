package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

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
    private String goodsPhotos;
    /**
     * 货号
     */
    private String goodsNo;
    /**
     * 物品属性集合LIST的JSON格式
     */
    private String attrList;
    private String goodsTypeId;
    private String goodsTypeName;
    private String stockNum;
    private String goodsUnit;

    /**
     * 规格x
     */
    private String x;

    /**
     * 规格y
     */
    private String y;

    /**
     * 规格列表
     */
    private List<GoodsSpec> goodsSpecList;
}
