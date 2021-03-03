package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: wd-saas
 * @description:出入库箱子
 * @author: Mr.Wang
 * @create: 2019-09-17 14:29
 **/
@Data
public class StockApplicationPackage implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 出入库主键
     */
    private String stockApplicationId;

    /**
     * 缸号
     */
    private String gang;
    /**
     * 包装编号
     */
    private String boxNum;
    /**
     * 包装数量
     */
    private String boxCount;
    /**
     * 重量单位
     */
    private String weightUnit;
    /**
     * 毛重
     */
    private String grossWeight;
    /**
     * 净重
     */
    private String netWeight;
    /**
     * 总毛重
     */
    private String totalGrossWeight;
    /**
     * 总净重
     */
    private String totalNetWeight;
    /**
     * 长度单位
     */
    private String lengthUnit;
    /**
     * 长
     */
    private String x;
    /**
     * 宽
     */
    private String y;
    /**
     * 高
     */
    private String z;
    /**
     * 体积
     */
    private String volume;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态 0 正常  1 删除
     */
    private String status;

    /**
     * 创建者
     */
    private String createUserId;

    /**
     * 排序号
     */
    private int sort;

    /**
     * 包装分类ID
     */
    private String categoryId;

    /**
     * 企业ID
     */
    private String enterpriseId;

    private String createTime;

    private String updateTime;

    private String updateUserId;

    private String deleteTime;

    private String deleteUserId;
}
