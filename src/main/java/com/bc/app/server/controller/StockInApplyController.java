package com.bc.app.server.controller;


import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.StockApplicationService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 入库申请
 *
 * @author zhou
 */
@RestController
@RequestMapping("/stockInApply")
public class StockInApplyController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(StockInApplyController.class);

    @Resource
    private StockApplicationService stockApplicationService;

    @ApiOperation(value = "新增入库申请", notes = "新增入库申请")
    @PostMapping(value = "")
    public ResponseEntity<String> addStockInApply(
            @RequestParam String goodsId,
            @RequestParam String createUserId,
            @RequestParam String enterpriseId,
            @RequestParam String stockType,
            @RequestParam String bizType,
            @RequestParam String specNums,
            @RequestParam String orderId,
            @RequestParam String relatedCompanyId,
            @RequestParam String applyStatus,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) String images,
            @RequestParam(required = false) String auditorId,
            @RequestParam(required = false) String copyId) {
        ResponseEntity<String> responseEntity;
        try {
            StockApplication s = new StockApplication();
            s.setStockGoodsId(goodsId);
            s.setCreateUserId(createUserId);
            s.setEnterpriseId(enterpriseId);
            s.setContactEnterpriseId(relatedCompanyId);
            s.setRemark(remark);
            s.setStockImg(images);
            s.setAuditUserId(auditorId);
            s.setCopyUserId(copyId);
            s.setOrderId(orderId);
            s.setBizType(bizType);
            s.setStockType(stockType);
            s.setApplyStatus(applyStatus);
            stockApplicationService.insert(s, specNums);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
