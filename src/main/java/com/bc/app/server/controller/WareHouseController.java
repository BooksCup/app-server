package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.WareHouse;
import com.bc.app.server.service.WareHouseService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whl
 */
@RestController
@RequestMapping("/wareHouse")
public class WareHouseController {

    @Resource
    private WareHouseService wareHouseService;

    @ApiOperation(value = "获取仓库列表", notes = "获取仓库列表")
    @GetMapping(value = "")
    public ResponseEntity<PageInfo<WareHouse>> getWareHouseList(
            @RequestParam String enterpriseId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String contactName,
            @RequestParam(required = false) String contactPhone,
            @RequestParam(required = false) String address,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<WareHouse>> responseEntity;
        try {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("name", name);
            paramMap.put("contactName", contactName);
            paramMap.put("contactPhone", contactPhone);
            paramMap.put("address", address);
            PageInfo<WareHouse> wareHousePageInfo = wareHouseService.getWareHouseList(paramMap, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(wareHousePageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 仓库详情中的出入列表
     *
     * @param id       仓库id
     * @param pageNum  当前页
     * @param pageSize 每页显示个数
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "仓库详情中的出入列表", notes = "仓库详情中的出入列表")
    @GetMapping(value = "/stockApplication/{id}")
    public ResponseEntity<PageInfo<Map<String, String>>> getStockInfoList(
            @PathVariable String id,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<Map<String, String>>> responseEntity;
        try {
            PageInfo<Map<String, String>> wareHousePageInfo = wareHouseService.getStockInfoList(id, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(wareHousePageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 通过仓库id查询仓库 库存
     *
     * @param id 库存id
     * @return 仓库库存信息
     */
    @CrossOrigin
    @ApiOperation(value = "通过仓库id查询仓库 库存", notes = "通过仓库id查询仓库 库存")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WareHouse> getWareHouseStockById(
            @PathVariable String id) {
        ResponseEntity<WareHouse> responseEntity;
        try {
            WareHouse wareHouse = wareHouseService.getWareHouseStockById(id);
            responseEntity = new ResponseEntity<>(wareHouse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new WareHouse(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 通过parent_id查询二级仓库列表
     *
     * @param id 仓库parentId
     * @return 二级仓库列表
     */
    @CrossOrigin
    @ApiOperation(value = "二级仓库列表", notes = "二级仓库列表")
    @GetMapping(value = "parent/{id}")
    public ResponseEntity<List<WareHouse>> getByParentId(
            @PathVariable String id) {
        ResponseEntity<List<WareHouse>> responseEntity;
        try {
            List<WareHouse> wareHouseList = wareHouseService.getByParentId(id);
            responseEntity = new ResponseEntity<>(wareHouseList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
