package com.bc.app.server.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author whl
 */
@Data
public class StockApplication implements Serializable {

    /**
     * 主键
     */
    private String id;

    /**
     * 状态 0未确认1已确认
     */
    private String status;

    /**
     * 是否删除1是0否
     */
    private String isDelete;

    /**
     * 物品主键
     */
    private String stockGoodsId;

    /**
     * 出入数量
     */
    private String stockNumber;

    /**
     * 申请数量
     */
    private String applyNumber;

    /**
     * 出入库类型 1入2出
     */
    private String stockType;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 企业主键
     */
    private String enterpriseId;

    /**
     * 往来企业主键
     */
    private String contactEnterpriseId;

    /**
     * 贸易单位主键
     */
    private String tradeEnterpriseId;

    /**
     * 接收人ID
     */
    private String receiverId;

    /**
     * 接收人
     */
    private String receiverName;

    /**
     * 接收地址
     */
    private String receiveAddress;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建人
     */
    private String createUserId;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 修改人
     */
    private String modifyUserId;
    /**
     * 修改时间
     */
    private String modifyTime;

    /**
     * 确认人
     */
    private String checkUserId;
    /**
     * 确认时间
     */
    private String checkTime;

    /**
     * 往来物料号
     */
    private String materialNumber;
    /**
     * 主题po
     */
    private String po;

    /**
     * 税率
     */
    private String tax;

    /**
     * 金额  不含税
     */
    private String totalAmount;

    /**
     * 原价 含税金额
     */
    private String costPrice;

    /**
     * 审核人ID
     */
    private String auditUserId;

    /**
     * 抄送人ID
     */
    private String copyUserId;

    /**
     * 出入库图片
     */
    private String stockImg;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 确认状态 0 草稿 1未确认 2已确认
     */
    private String applyStatus;

    /**
     * 币种
     */
    private String currency;

    /**
     * 出入库详细
     */
    private List<StockApplicationInRecord> stockApplicationInRecordList;

    /**
     * 入库订单信息
     */
    private List<StockApplicationOrder> stockApplicationOrderList;
}
