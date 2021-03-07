package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.entity.vo.StockApplicationVo;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.StockApplicationService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
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
            stockApplicationService.insert(stockApplication, null);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取出入库申请分页信息
     *
     * @param enterpriseId 企业ID
     * @param stockType    出入库类型
     * @param pageNum      当前分页数
     * @param pageSize     分页大小
     * @return 出入库申请分页信息
     */
    @ApiOperation(value = "获取出入库申请分页信息", notes = "获取出入库申请分页信息")
    @GetMapping(value = "")
    public ResponseEntity<PageInfo<StockApplication>> getStockApplicationList(
            @RequestParam String enterpriseId,
            @RequestParam String stockType,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<StockApplication>> responseEntity;
        try {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("stockType", stockType);
            PageInfo<StockApplication> stockApplicationPageInfo = stockApplicationService.getStockApplicationList(paramMap, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(stockApplicationPageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
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
