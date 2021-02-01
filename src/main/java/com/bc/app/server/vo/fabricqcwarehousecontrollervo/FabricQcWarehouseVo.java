package com.bc.app.server.vo.fabricqcwarehousecontrollervo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FabricQcWarehouseVo implements Serializable {
     private String productName;
     private String productId;
     private String goodsImage;
     private String goodsNo;
     private String width;
     private String gramWeight;
    private List<QcWarehouseSupplierIdVo> qcWarehouseSupplierIdVos;
}
