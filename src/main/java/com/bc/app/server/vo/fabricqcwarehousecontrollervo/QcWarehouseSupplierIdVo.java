package com.bc.app.server.vo.fabricqcwarehousecontrollervo;

import com.bc.app.server.entity.FabricQcWarehouse;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QcWarehouseSupplierIdVo implements Serializable{

    private String  supplierName;
    private String supplierId;
    private List<FabricQcWarehouse> fabricQcWarehouseList;
}
