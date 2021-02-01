package com.bc.app.server.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * 面料验货记录表
 */
@Data
public class FabricQcRecord implements Serializable {

     private String  id;
     private String  sno;
     private String  cylinderNumber;
     private String  lengthBefore;
     private String lengthAfter;
     private String lengthUnit;
     private String weightBefore;
     private String weightAfter;
     private String weightUnit;
     private String createTime;
     private String  modifyTime;
     private String isDelete;
     private String warehouseId;
     private String remark;

}
