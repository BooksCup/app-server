package com.bc.app.server.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 面料验货记录表
 */
@Data
public class FabricCheckRecord implements Serializable {

    private String id;
    private String sno;
    /**
     * 面料盘点-缸信息表id
     */
    private String checkLotInfoId;
    private String lengthBefore;
    private String lengthAfter;
    private String lengthUnit;
    private String weightBefore;
    private String weightAfter;
    private String weightUnit;
    private String deliveryDate;
    private String problemCount;
    private String createTime;
    private String modifyTime;
    private String isDelete;
    private  String widthTop;
    private  String widthMiddle;
    private  String widthBottom;

    private FabricCheckRecordProblem fabricCheckRecordProblem;
}
