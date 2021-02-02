package com.bc.app.server.controller;


import com.alibaba.fastjson.JSON;
import com.bc.app.server.entity.FabricQcRecord;
import com.bc.app.server.entity.FabricQcRecordProblem;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.FabricQcRecordProblemService;
import com.bc.app.server.service.FabricQcRecordService;
import com.bc.app.server.service.FabricQcWarehouseService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 面料验货记录
 */
@RestController
@RequestMapping("/fabricQcRecord")
public class FabricQcRecordController {


    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);


    @Autowired
    private FabricQcRecordService fabricQcRecordService;

    @Autowired
    private FabricQcRecordProblemService fabricQcRecordProblemService;

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
            FabricQcRecord fabricQcRecord = new FabricQcRecord();
            fabricQcRecord.setId(id);
            fabricQcRecord = fabricQcRecordService.selectById(fabricQcRecord);
            SelectByIdVo selectByIdVo = new SelectByIdVo();
            BeanUtils.copyProperties(fabricQcRecord, selectByIdVo);
            FabricQcRecordProblem fabricQcRecordProblem = new FabricQcRecordProblem();
            fabricQcRecordProblem.setRecordId(id);
            List<FabricQcRecordProblem> list = fabricQcRecordProblemService.getCountData(fabricQcRecordProblem);
            if (CollectionUtils.isNotEmpty(list)) {
                for (FabricQcRecordProblem fabricQcRecordProblem1 : list) {
                    if (!StringUtils.isEmpty(fabricQcRecordProblem1.getImage())) {
                        fabricQcRecordProblem1.setImage(fabricQcRecordProblem1.getImage().replace("];[", ",").replace(";", "").replace("[]", ""));
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
     * @param string  json字符串
     * @return
     */
    @ApiOperation(value = "通过id更新数据集合", notes = "通过id更新数据集合")
    @PostMapping(value = "/updateByIds")
    public ResponseEntity<String> updateByIds(
            @RequestParam String string) {
        List<UpdateByIdVo> list = JSON.parseObject(string, List.class);
        Integer integer = 0;
        if (CollectionUtils.isNotEmpty(list)) {
            integer = fabricQcRecordService.updateListById(list);
        }
        if (integer > 0) {
            return new ResponseEntity<>(ResponseMsg.
                    UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseMsg.
                UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * 通过id更新数据
     * @param id id
     * @param lengthAfter  检查之后长度
     * @param weightAfter  检查之后重量
     * @param remark       备注信息
     * @return
     */
    @ApiOperation(value = "通过id更新数据", notes = "通过id更新数据")
    @PutMapping(value = "/updateById")
    public ResponseEntity<String> updateById(
            @RequestParam(value = "id") String id,
            @RequestParam(value = "lengthAfter", required = false) String lengthAfter,
            @RequestParam(value = "weightAfter", required = false) String weightAfter,
            @RequestParam(value = "remark", required = false) String remark) {

        Map<String, String> map = new HashMap<>();
        map.put("remark", remark);
        map.put("weightAfter", weightAfter);
        map.put("lengthAfter", lengthAfter);
        map.put("id", id);
        Integer integer = fabricQcRecordService.updateById(map);
        if (integer > 0) {
            return new ResponseEntity<>(ResponseMsg.
                    UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseMsg.
                UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 通过总记录表id查询记录本数据
     *
     * @param  warehouseId 面料入库总记录表id
     * @return
     */
    @ApiOperation(value = "通过总记录表id查询数据记录列表", notes = "通过总记录表id查询数据记录列表")
    @GetMapping(value = "/getByWarehouseId")
    public ResponseEntity<GetByWarehouseIdVo> getByWarehouseId(
            @RequestParam(value = "warehouseId") String warehouseId) {
        ResponseEntity<GetByWarehouseIdVo> responseEntity;
        try {
            FabricQcRecord fabricQcRecord = new FabricQcRecord();
            fabricQcRecord.setWarehouseId(warehouseId);
            GetByWarehouseIdVo countData = fabricQcRecordService.getCountData(fabricQcRecord);
            if (countData == null) {
                return new ResponseEntity<>(new GetByWarehouseIdVo(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
            List<FabricQcRecord> fabricQcRecordList = fabricQcRecordService.getByWarehouseId(fabricQcRecord);
            countData.setFabricQcRecordList(fabricQcRecordList);
            responseEntity = new ResponseEntity<>(countData, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new GetByWarehouseIdVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}