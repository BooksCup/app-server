package com.bc.app.server.mapper;


import com.bc.app.server.entity.FabricQcWarehouse;
import com.bc.app.server.entity.Goods;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.FabricQcWarehouseVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.QcWarehouseSupplierIdVo;
import com.github.pagehelper.Page;
import io.swagger.models.auth.In;

import java.util.List;
import java.util.Map;

/**
 * 面料入库总记录
 */
public interface FabricQcWarehouseMapper {

    Integer insertQcWarehouse(FabricQcWarehouse fabricQcWarehouse);

    Page<FabricQcWarehouse> getPage();

    List<FabricQcWarehouse> getAppPage(Map<String, String> map);

    Integer update(FabricQcWarehouse fabricQcWarehouse);

    Page<FabricQcWarehouseVo> getListGroupProductId(Map<String, String> map);

    List<QcWarehouseSupplierIdVo> getListGroupSupplierId(Map<String, String> map);

    Goods getGoods(Map<String, String> goodMap);
}
