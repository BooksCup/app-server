package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: wd-saas
 * @description: 包装箱分类
 * @author: Mr.Wang
 * @create: 2019-10-25 14:20
 **/
@Data
public class StockApplicationPackageCategory implements Serializable {

    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 缸号
     */
    private String gang;
    /**
     * 出入库主键
     */
    private String stockApplicationId;
    /**
     * 包装名称
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
     * 箱子
     */
    private List<StockApplicationPackage> stockApplicationPackageList;

    /**
     * 原ID
     */
    private String oldId;
    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 当前分类是否在前端展开（展开过为空则删除所有箱子以及记录，如果没展开就不需要编辑或者修改分类下的箱子）
     */
    private String openCategory;

    /**
     * 当前分类是否加载了所有箱子
     */
    private String showAllPackage;

    /**
     * 次分类下的总数量
     */
    private String count;
}
