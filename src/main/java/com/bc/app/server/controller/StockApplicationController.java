package com.bc.app.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.Goods;
import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.entity.StockApplicationInRecord;
import com.bc.app.server.entity.vo.StockApplicationVo;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.StockApplicationService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

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
     * 获取出入库申请(入库单/出库单)分页信息
     *
     * @param enterpriseId 企业ID
     * @param stockType    出入库类型
     * @param pageNum      当前分页数
     * @param pageSize     分页大小
     * @return 出入库申请分页信息
     */
    @ApiOperation(value = "获取出入库申请(入库单/出库单)分页信息", notes = "获取出入库申请(入库单/出库单)分页信息")
    @GetMapping(value = "")
    public ResponseEntity<PageInfo<StockApplication>> getStockApplicationList(
            @RequestParam String enterpriseId,
            @RequestParam String stockType,
            @RequestParam(required = false) String role,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<StockApplication>> responseEntity;
        try {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("stockType", stockType);
            if (!StringUtils.isEmpty(role)) {
                paramMap.put("role", role);
            }
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
    public ResponseEntity<StockApplicationVo> StockApplicationDetail(
            @PathVariable String id) {
        ResponseEntity<StockApplicationVo> responseEntity;
        try {
            StockApplicationVo stockApplication = stockApplicationService.findById(id);
            responseEntity = new ResponseEntity<>(stockApplication, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new StockApplicationVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 修改出入库详情信息
     *
     * @param id                   id
     * @param stockApplicationJson 出入库详情json
     * @return
     */
    @ApiOperation(value = "修改出入库详情", notes = "修改出入库详情")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateStockApplication(
            @PathVariable String id,
            @RequestParam String stockApplicationJson) {
        ResponseEntity<String> responseEntity;
        try {
            logger.info("[updateStockApplication], id: " + id + ", stockApplicationJson: " + stockApplicationJson);
            StockApplication stockApplication = JSON.parseObject(stockApplicationJson, StockApplication.class);
            stockApplication.setId(id);
            stockApplicationService.updateStockApplication(stockApplication);
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 物品库存记录
     *
     * @param goodsId
     * @return
     */
    @CrossOrigin
    @ApiOperation(value = "物品库存记录", notes = "物品库存记录")
    @GetMapping(value = "/in/{goodsId}/stock")
    public ResponseEntity<Goods> getStockByGoodsId(
            @PathVariable String goodsId) {
        ResponseEntity<Goods> responseEntity;
        try {
            Goods goods = stockApplicationService.getStockByGoodsId(goodsId);
            responseEntity = new ResponseEntity<>(goods, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new Goods(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
