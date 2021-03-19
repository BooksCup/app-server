package com.bc.app.server.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;

/**
 *  出库记录
 **/
@Data
public class StockApplicationOutRecord implements Serializable {


    /**
     * 主键
     */
    private String id;

    /**
     * 入库记录ID
     */
    private String inRecordId;

    /**
     * 出入库ID
     */
    private String stockApplicationId;

    /**
     * 仓库ID
     */
    private String wareHouseId;

    /**
     * 物品ID
     */
    private String goodsId;

    /**
     * 物品规格ID
     */
    private String goodsSpecId;

    /**
     * 出库数量
     */
    private String count;

    /**
     * 出库价格
     */
    private String price;

    /**
     * 创建者
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 箱子数量
     */
    private String boxNum;

    /**
     * 箱子ID
     */
    private String boxId;

    /**
     * 企业ID
     */
    private String enterpriseId;

    /**
     * 排序
     */
    private int sort;

    /**
     * 重量单位
     */
    private String weightUnit;

    /**
     * 长度单位
     */
    private String lengthUnit;

    /**
     * 币种
     */
    private String currency;

    /**
     * 汇率
     */
    private String rate;

    /**
     * 当时入库的剩余数量
     */
    private String currentInResidualNumber;

    /**
     * 物品X
     */
    private String x;

    /**
     * 物品Y
     */
    private String y;

    /**
     * 入库剩余数量
     */
    private String residualNumber;

    /**
     * 入库最初库存
     */
    private String totalResidualNumber;

    /**
     * 入库记录
     */
    private StockApplicationInRecord stockApplicationInRecord;

    /**
     * 仓库信息
     */
    private WareHouse wareHouse;

    /**
     * 相关订单
     */
    private List<StockApplicationOrder> stockApplicationOrderList;

   /**
     * 备注
     */
    private String remark;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 入库的业务类型
     */
    private String inBizType;

    /**
     * 入库的金额
     */
    private String inPrice;
}
