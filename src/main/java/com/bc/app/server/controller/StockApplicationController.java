package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.entity.vo.StockApplicationVo;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.StockApplicationService;
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
@RequestMapping("/stockApplication")
public class StockApplicationController {

    @Resource
    private StockApplicationService stockApplicationService;

    @ApiOperation(value = "新增入库", notes = "新增入库")
    @PostMapping(value = "")
    public ResponseEntity<String> insertStockApplication(
            @RequestBody StockApplication stockApplication) {
        ResponseEntity<String> responseEntity;
        try {
            stockApplicationService.insert(stockApplication,null);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @ApiOperation(value = "获取出入库列表", notes = "获取出入库列表")
    @GetMapping(value = "")
    public ResponseEntity<List<StockApplication>> getStockApplicationList(
            @RequestParam String enterpriseId,
            @RequestParam String stockType) {
        ResponseEntity<List<StockApplication>> responseEntity;
        try {
            Map<String, Object> paramsMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramsMap.put("enterpriseId", enterpriseId);
            paramsMap.put("stockType", stockType);
            List<StockApplication> stockApplicationList = stockApplicationService.getStockApplicationList(paramsMap);
            responseEntity = new ResponseEntity<>(stockApplicationList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @ApiOperation(value = "查询入库详情", notes = "查询入库详情")
    @GetMapping(value = "/{id}")
    public ResponseEntity<StockApplicationVo> insertStockApplication(
            @PathVariable String id) {
        ResponseEntity<StockApplicationVo> responseEntity;
        try {
            StockApplicationVo stockApplication = stockApplicationService.findById(id);
            responseEntity = new ResponseEntity<StockApplicationVo>(stockApplication, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<StockApplicationVo>(new StockApplicationVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
