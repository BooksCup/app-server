package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author whl
 */
@Data
public class WareHouse implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 父主键
     */
    private String parentId;

    /**
     * 仓库名称
     */
    private String name;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区域
     */
    private String area;

    /**
     * 详细地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者
     */
    private String createUserId;

    /**
     * 创建者
     */
    private String createUserName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 1内部仓库0外部仓库
     */
    private String isOwn;

    /**
     * 子仓库
     */
    private List<WareHouse> children;

    /**
     * 子仓库数量
     */
    private String wareHouseChildrenCount;

    /**
     * 入库信息
     */
    private List<StockApplicationInRecord> stockApplicationInRecordList;

    /**
     * 层级
     */
    private String level;

}
