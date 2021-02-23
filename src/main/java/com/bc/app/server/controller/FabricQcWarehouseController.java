package com.bc.app.server.controller;


import com.bc.app.server.entity.FabricQcWarehouse;
import com.bc.app.server.service.FabricQcWarehouseService;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.FabricQcWarehouseVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.PageResult;
import io.swagger.annotations.ApiOperation;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 面料入库总记录表
 */
@RestController
@RequestMapping("/fabricQcWarehouse")
public class FabricQcWarehouseController {


    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

    @Autowired
    FabricQcWarehouseService fabricQcWarehouseService;

    /**
     * 布料数据导入到数据库
     *
     * @param
     * @param productName  产品名称
     * @param productId    产品id
     * @param supplierName 供应商名称
     * @param supplierId   供应商id
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "布料数据导入到数据库", notes = "布料数据导入到数据库")
    @PostMapping(value = "/importExcel")
    @MultipartForm
    public Map<String, Object> importExcel(
            @RequestParam("file") MultipartFile file, @RequestParam("productName") String productName,
            @RequestParam("productId") String productId, @RequestParam("supplierName") String supplierName,
            @RequestParam("supplierId") String supplierId) throws IOException {
        Map<String, Object> map = new HashMap<>();
        boolean b = fabricQcWarehouseService.importExcel(file, productName, productId, supplierName, supplierId);
        if (b) {
            map.put("code", 200);
            map.put("message", "导入成功");
        } else {
            map.put("code", 500);
            map.put("message", "导入失败");
        }
        return map;
    }

    /**
     * pc分页查询
     *
     * @param pageSize 每页显示个数
     * @param pageNum  当前页
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "pc分页查询", notes = "pc分页查询")
    @GetMapping("/page")
    public PageResult<List<FabricQcWarehouse>> getPage(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("pageNum") Integer pageNum) {
        PageResult<List<FabricQcWarehouse>> page = fabricQcWarehouseService.getPage(pageNum, pageSize);
        return page;
    }


    /**
     * app盘点分页查询列表
     * @param pageSize 每页显示个数
     * @param pageNum  当前页
     * @param oddNumber 订单号
     * @param cylinderNumber 缸号
     * @param supplierName  供应商名称
     * @param productName  产品名称
     * @return
     */
    @ApiOperation(value = "app盘点分页查询列表", notes = "app盘点分页查询列表")
        @GetMapping("/getAppPage")
    public PageResult<List<FabricQcWarehouseVo>> getAppPage(
            @RequestParam("pageSize") Integer pageSize,
            @RequestParam("pageNum") Integer pageNum,
            @RequestParam(required = false, value = "oddNumber") String oddNumber, @RequestParam(required = false, value = "cylinderNumber") String cylinderNumber,
            @RequestParam(required = false, value = "supplierName") String supplierName,
            @RequestParam(required = false, value = "productName") String productName,
            @RequestParam( value = "enterpriseId") String enterpriseId) {
        Map<String, String> map = new HashMap<>();
        map.put("oddNumber", oddNumber);
        map.put("cylinderNumber", cylinderNumber);
        map.put("supplierName", supplierName);
        map.put("productName", productName);
        map.put("enterpriseId", enterpriseId);
        PageResult<List<FabricQcWarehouseVo>> appPage = fabricQcWarehouseService.getAppPage(pageNum, pageSize, map);
        return appPage;
    }


    /**
     * 通过ID 删除数据
     * @param id 布料总数据表id
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "删除", notes = "删除")
    @GetMapping("/{id}")
    public Map<Object, Object> delete(@PathVariable String id) {
        Map<Object, Object> responseEntity = new HashMap<>();
        try {
            FabricQcWarehouse fabricQcWarehouse = new FabricQcWarehouse();
            fabricQcWarehouse.setId(id);
            Integer delete = fabricQcWarehouseService.delete(fabricQcWarehouse);
            if (delete > 0) {
                responseEntity.put("code", 0);
                responseEntity.put("message", "删除成功");
            } else {
                responseEntity.put("code", 1000);
                responseEntity.put("message", "删除失败,请联系管理员");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[delete] error: " + e.getMessage());
            responseEntity.put("code", 1000);
            responseEntity.put("message", "删除失败,请联系管理员");
        }
        return responseEntity;
    }


}