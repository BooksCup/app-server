package com.bc.app.server.entity;

import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
import lombok.Data;

import java.util.List;

/**
 * 面料盘点任务
 *
 * @author qiu
 */
@Data
public class FabricCheckTask {

    private String id;
    private String goodsId;
    private String enterpriseId;
    private String goodsName;
    private String goodsNo;
    private String goodsPhotos;
    private String relatedCompanyName;
    private String relatedCompanyShortName;
    private String relatedCompanyId;
    private String orderId;
    private String orderNo;
    private String orderTheme;
    private String deliveryDate;
    private List<FabricCheckLotInfo> fabricCheckLotInfoList;


    private String isDelete;
    private String createDate;

    public FabricCheckTask(String goodsName, String goodsNo, String goodsPhotos, String goodsId,
                           String relatedCompanyName, String relatedCompanyShortName,
                           String orderNo, String orderTheme, String orderId, String relatedCompanyId,
                           String enterpriseId, String deliveryDate) {
        this.id = CommonUtil.generateId();
        this.goodsName = goodsName;
        this.goodsNo = goodsNo;
        this.goodsPhotos = goodsPhotos;
        this.goodsId = goodsId;
        this.relatedCompanyName = relatedCompanyName;
        this.relatedCompanyShortName = relatedCompanyShortName;
        this.orderNo = orderNo;
        this.orderTheme = orderTheme;
        this.orderId = orderId;
        this.relatedCompanyId = relatedCompanyId;
        this.enterpriseId = enterpriseId;
        this.deliveryDate = deliveryDate;
    }

    public FabricCheckTask() {
    }

    public List<FabricCheckLotInfo> getFabricCheckLotInfoList() {
        return fabricCheckLotInfoList;
    }

    public void setFabricCheckLotInfoList(List<FabricCheckLotInfo> fabricCheckLotInfoList) {
        this.fabricCheckLotInfoList = fabricCheckLotInfoList;
    }

}
