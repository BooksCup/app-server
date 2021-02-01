package com.bc.app.server.entity;


import lombok.Data;

import java.io.Serializable;

@Data
public class FabricQcWarehouse implements Serializable{
     private String id;
     private String productName;
     private String productId;
     private String  supplierName;
    private String supplierId;
    private String cylinderNumber;
    private String oddNumber;
    private String width;
    private String widthUnit;
    private String gramWeight;
    private String totalLength;
    private String totalWeight;
    private String colour;
    private String qcNumber;
    private String lengthUnit;
    private String weightUnit;
    private String createTime;
    private String modifyTime;
    private String isDelete;
    private String remark;
    private String oddTime;
    private String goodsImage;
    private String goodsNo;
}
