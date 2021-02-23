package com.bc.app.server.controller;


import com.bc.app.server.entity.FabricCheckLotInfo;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.FabricCheckLotInfoService;
import com.bc.app.server.utils.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 面料盘点-缸信息
 *
 * @author qiu
 */

@RestController
@RequestMapping("/fabricCheckLotInfo")
public class FabricCheckLotInfoController {

    @Autowired
    FabricCheckLotInfoService fabricCheckLotInfoService;

    /**
     * 添加面料盘点-缸信息
     *
     * @param fabricCheckTaskId 面料盘点任务表id
     * @param lotNo             缸号
     * @param num               卷数
     * @param length            数量
     * @param weight            重量
     * @return 添加结果信息
     */
    @ApiOperation(value = "添加面料盘点-缸信息", notes = "添加面料盘点-缸信息")
    @PostMapping(value = "/addFabricCheckLotInfo")
    public ResponseEntity<String> addFabricCheckLotInfo(
            @RequestParam String fabricCheckTaskId,
            @RequestParam(required = false) String lotNo,
            @RequestParam(required = false) String num,
            @RequestParam(required = false) String length,
            @RequestParam(required = false) String weight) {
        ResponseEntity<String> responseEntity;
        try {
            FabricCheckLotInfo fabricCheckLotInfo = new FabricCheckLotInfo();
            fabricCheckLotInfo.setId(CommonUtil.generateId());
            fabricCheckLotInfo.setLength(length);
            fabricCheckLotInfo.setLotNo(lotNo);
            fabricCheckLotInfo.setNum(num);
            fabricCheckLotInfo.setFabricCheckTaskId(fabricCheckTaskId);
            fabricCheckLotInfo.setWeight(weight);
            fabricCheckLotInfoService.addFabricCheckLotInfo(fabricCheckLotInfo);
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 更新面料盘点缸信息表信息
     *
     * @param id 面料盘点-缸信息表id
     * @return ResponseEntity
     */
    @ApiOperation(value = "更新面料盘点-缸信息表信息", notes = "更新面料盘点-缸信息表信息")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateById(
            @PathVariable String id,
            @RequestParam(required = false) String isDelete) {
        ResponseEntity<String> responseEntity;
        try {
            Map<String, String> map = new HashMap<>();
            map.put("id", id);
            map.put("isDelete", isDelete);
            fabricCheckLotInfoService.updateById(map);
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
