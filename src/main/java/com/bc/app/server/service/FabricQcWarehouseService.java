package com.bc.app.server.service;

import com.bc.app.server.entity.FabricQcWarehouse;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.FabricQcWarehouseVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * 面料入库总记录
 */
public interface FabricQcWarehouseService {


    /**
     * 导入布料数入库
     *
     * @param
     */
    boolean importExcel(MultipartFile file, String productName,
                        String productId, String supplierName,
                        String supplierId) throws IOException;

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResult<List<FabricQcWarehouse>> getPage(Integer pageNum, Integer pageSize);

    /**
     * app盘点分页查询列表
     *
     * @param pageNum
     * @param pageSize
     * @param map
     */
    PageResult<List<FabricQcWarehouseVo>> getAppPage(Integer pageNum, Integer pageSize, Map<String, String> map);


    /**
     * 删除数据
     * @author Mars
     * @param fabricQcWarehouse
     * @return
     */
    Integer delete(FabricQcWarehouse fabricQcWarehouse);

}