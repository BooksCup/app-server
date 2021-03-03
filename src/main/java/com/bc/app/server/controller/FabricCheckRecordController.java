package com.bc.app.server.controller;


import com.alibaba.fastjson.JSON;
import com.bc.app.server.entity.FabricCheckRecord;
import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.FabricCheckRecordProblemService;
import com.bc.app.server.service.FabricCheckRecordService;
import com.bc.app.server.service.FabricQcWarehouseService;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.FabricQcRecordAllByCheckLIIdVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.GetByWarehouseIdVo;
import com.bc.app.server.vo.fabricqcrecordcontrollervo.SelectByIdVo;
import com.bc.app.server.vo.fabricqcwarehousecontrollervo.UpdateByIdVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 面料验货记录
 *
 * @author qiu
 */
@RestController
@RequestMapping("/fabricCheckRecord")
public class FabricCheckRecordController {


    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);


    @Autowired
    private FabricCheckRecordService fabricCheckRecordService;

    @Autowired
    private FabricCheckRecordProblemService fabricCheckRecordProblemService;

    @Autowired
    FabricQcWarehouseService fabricQcWarehouseService;

    /**
     * 通过id查询数据
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "盘点详情列表", notes = "盘点详情列表")
    @GetMapping(value = "/{id}")
    public ResponseEntity<SelectByIdVo> selectById(
            @PathVariable String id) {
        ResponseEntity<SelectByIdVo> responseEntity;
        try {
            FabricCheckRecord fabricCheckRecord = new FabricCheckRecord();
            fabricCheckRecord.setId(id);
            fabricCheckRecord = fabricCheckRecordService.selectById(fabricCheckRecord);
            SelectByIdVo selectByIdVo = new SelectByIdVo();
            BeanUtils.copyProperties(fabricCheckRecord, selectByIdVo);
            FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem();
            fabricCheckRecordProblem.setRecordId(id);
            List<FabricCheckRecordProblem> list = fabricCheckRecordProblemService.getCountData(fabricCheckRecordProblem);
            if (CollectionUtils.isNotEmpty(list)) {
                for (FabricCheckRecordProblem fabricCheckRecordProblem1 : list) {
                    if (!StringUtils.isEmpty(fabricCheckRecordProblem1.getImage())) {
                        fabricCheckRecordProblem1.setImage(fabricCheckRecordProblem1.getImage().replace("];[", ",").replace(";", "").replace("[]", ""));
                    }
                }
            }
            selectByIdVo.setRecordProblemList(list);
            responseEntity = new ResponseEntity<>(selectByIdVo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[selectById] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new SelectByIdVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 通过id更新数据集合
     *
     * @param fabricCheckRecords json字符串
     * @return
     */
    @ApiOperation(value = "通过id更新数据集合", notes = "通过id更新数据集合")
    @PutMapping(value = "")
    public ResponseEntity<String> batchUpdateFabricCheckRecordByIds(
            @RequestParam String fabricCheckRecords) {
        try {
            List<FabricCheckRecord> list = JSON.parseArray(fabricCheckRecords, FabricCheckRecord.class);
            fabricCheckRecordService.batchUpdateFabricCheckRecordByIds(list);
            return new ResponseEntity<>(ResponseMsg.
                    UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseMsg.
                    UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 通过总记录表id查询记录数据
     *
     * @param warehouseId 面料入库总记录表id
     * @return
     */
    @ApiOperation(value = "通过总记录表id查询数据记录列表", notes = "通过总记录表id查询数据记录列表")
    @GetMapping(value = "/getByWarehouseId")
    public ResponseEntity<GetByWarehouseIdVo> getByWarehouseId(
            @RequestParam(value = "warehouseId") String warehouseId) {
        ResponseEntity<GetByWarehouseIdVo> responseEntity;
        try {
            FabricCheckRecord fabricCheckRecord = new FabricCheckRecord();
            GetByWarehouseIdVo countData = fabricCheckRecordService.getCountData(fabricCheckRecord);
            if (countData == null) {
                return new ResponseEntity<>(new GetByWarehouseIdVo(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            List<FabricCheckRecord> fabricCheckRecordList = fabricCheckRecordService.getByWarehouseId(fabricCheckRecord);
            countData.setFabricCheckRecordList(fabricCheckRecordList);
            responseEntity = new ResponseEntity<>(countData, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GetByWarehouseIdVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 批量保存检查记录信息
     *
     * @param fabricCheckRecords json字符串
     * @return
     */
    @ApiOperation(value = "批量保存检查记录信息", notes = "批量保存检查记录信息")
    @PostMapping(value = "")
    public ResponseEntity<List<FabricCheckRecord>> insertFabricQcRecords(
            @RequestParam String fabricCheckRecords) {

        ResponseEntity<List<FabricCheckRecord>> responseEntity;
        try {
            List<FabricCheckRecord> list = JSON.parseArray(fabricCheckRecords, FabricCheckRecord.class);
            List<FabricCheckRecord> fabricCheckRecordList = fabricCheckRecordService.insertFabricQcRecords(list);
            responseEntity = new ResponseEntity<>(fabricCheckRecordList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 通过面料盘点-缸信息表id获取检查记录表信息
     *
     * @param checkLotInfoId 面料盘点-缸信息表id
     * @return 检查记录表信息
     */
    @ApiOperation(value = "通过面料盘点-缸信息表id获取检查记录表信息", notes = "通过面料盘点-缸信息表id获取检查记录表信息")
    @GetMapping(value = "/searchAll")
    public ResponseEntity<List<FabricQcRecordAllByCheckLIIdVo>> getFabricQcRecordAllByCheckLIId(
            @RequestParam String checkLotInfoId) {
        ResponseEntity<List<FabricQcRecordAllByCheckLIIdVo>> responseEntity;
        Map<String, String> map = new HashMap<>();
        map.put("checkLotInfoId", checkLotInfoId);
        try {
            List<FabricQcRecordAllByCheckLIIdVo> pageInfo = fabricCheckRecordService.getFabricQcRecordAllByCheckLIId(map);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 删除数据
     *
     * @param id id
     * @return
     */
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @PutMapping(value = "/updateById")
    public ResponseEntity<String> updateById(
            @RequestParam(value = "id") String id) {

        Map<String, String> map = new HashMap<>();
        map.put("remark", "");
        map.put("weightAfter", "");
        map.put("lengthAfter", "");
        map.put("isDelete", "1");
        map.put("id", id);
        Integer integer = fabricCheckRecordService.updateById(map);
        if (integer > 0) {
            return new ResponseEntity<>(ResponseMsg.
                    UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseMsg.
                UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}