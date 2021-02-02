package com.bc.app.server.vo.fabricqcwarehousecontrollervo;


import com.bc.app.server.entity.Goods;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FabricQcWarehouseVo implements Serializable {

     private String productName;

     private String productId;

     private Goods goods;

     private List<QcWarehouseSupplierIdVo> qcWarehouseSupplierIdVos;

}