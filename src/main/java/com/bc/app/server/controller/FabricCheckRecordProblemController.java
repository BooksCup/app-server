package com.bc.app.server.controller;


import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.FabricCheckRecordProblemService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 面料验货问题配置
 */
@RestController
@RequestMapping("/fabricQcRecordProblem")
public class FabricCheckRecordProblemController {


    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);


    @Autowired
    private FabricCheckRecordProblemService fabricCheckRecordProblemService;

    /**
     * 新增问题记录
     *
     * @param recordId  记录信息表id
     * @param tag       问题标记
     * @param tagATimes A问题等级
     * @param tagBTimes B问题等级
     * @param tagCTimes C问题等级
     * @param tagDTimes D问题等级
     * @param remark    备注
     * @param image     问题照片
     * @return
     */
    @ApiOperation(value = "新增问题记录", notes = "新增问题记录")
    @PostMapping(value = "")
    public ResponseEntity<String> addFabricQcRecordProblem(
            @RequestParam String recordId,
            @RequestParam String tag,
            @RequestParam(required = false, value = "tagATimes") String tagATimes,
            @RequestParam(required = false, value = "tagBTimes") String tagBTimes,
            @RequestParam(required = false, value = "tagCTimes") String tagCTimes,
            @RequestParam(required = false, value = "tagDTimes") String tagDTimes,
            @RequestParam(required = false, value = "remark") String remark,
            @RequestParam(required = false, value = "image") String image) {
        ResponseEntity<String> responseEntity;
        try {
            FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem("", recordId, tag, tagATimes, tagBTimes, tagCTimes, tagDTimes, remark, image);
            fabricCheckRecordProblemService.addFabricQcRecordProblem(fabricCheckRecordProblem);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseMessage(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[addFabricQcProblemConfig] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 通过验货表id查询问题集合
     *
     * @param recordId 记录信息表id
     * @param tag      问题记录
     * @return
     */
    @ApiOperation(value = "通过验货表id查询问题集合", notes = "通过验货表id查询问题集合")
    @GetMapping(value = "")
    public ResponseEntity<List<FabricCheckRecordProblem>> getFabricQcRecordProblemByRecordId(
            @RequestParam String recordId,
            @RequestParam(required = false, value = "tag") String tag) {
        ResponseEntity<List<FabricCheckRecordProblem>> responseEntity;
        try {
            FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem();
            fabricCheckRecordProblem.setRecordId(recordId);
            fabricCheckRecordProblem.setTag(tag);
            List<FabricCheckRecordProblem> list = fabricCheckRecordProblemService.getFabricQcRecordProblemByRecordId(fabricCheckRecordProblem);

            responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[getFabricQcRecordProblemByRecordId] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 通过记录表id进行删除数据
     *
     * @param id 问题记录表id
     * @return
     */
    @ApiOperation(value = "通过记录表id进行删除数据", notes = "通过记录表id进行删除数据")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(
            @PathVariable String id) {
        ResponseEntity<String> responseEntity;
        try {
            FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem();
            fabricCheckRecordProblem.setId(id);
            Integer delete = fabricCheckRecordProblemService.delete(fabricCheckRecordProblem);
            if (delete > 0) {
                responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_SUCCESS.getResponseMessage(), HttpStatus.OK);
            } else {
                responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_ERROR.getResponseMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[delete] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_ERROR.getResponseMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 通过id更新数据
     *
     * @param recordId  记录信息表id
     * @param tag       问题标记
     * @param tagATimes A问题等级
     * @param tagBTimes B问题等级
     * @param tagCTimes C问题等级
     * @param tagDTimes D问题等级
     * @param remark    备注
     * @param image     问题照片
     * @return
     */
    @ApiOperation(value = "通过id更新数据", notes = "通过id更新数据")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateById(
            @PathVariable String id,
            @RequestParam(required = false, value = "tag") String tag,
            @RequestParam(required = false, value = "tagATimes") String tagATimes,
            @RequestParam(required = false, value = "tagATimes") String tagBTimes,
            @RequestParam(required = false, value = "tagCTimes") String tagCTimes,
            @RequestParam(required = false, value = "tagDTimes") String tagDTimes,
            @RequestParam(required = false, value = "remark") String remark,
            @RequestParam(required = false, value = "image") String image) {
        ResponseEntity<String> responseEntity;
        try {
            FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem(id, tag, tagATimes, tagBTimes, tagCTimes, tagDTimes, remark, image);
            fabricCheckRecordProblemService.updateById(fabricCheckRecordProblem);
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_SUCCESS.getResponseMessage(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[updateById] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_ERROR.getResponseMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 通过id查询数据
     *
     * @param id 问题记录表id
     * @return
     */
    @ApiOperation(value = "通过id查询数据", notes = "通过id查询数据")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FabricCheckRecordProblem> selectById(
            @PathVariable String id) {
        ResponseEntity<FabricCheckRecordProblem> responseEntity;
        try {
            FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem();
            fabricCheckRecordProblem.setId(id);
            fabricCheckRecordProblem = fabricCheckRecordProblemService.selectById(fabricCheckRecordProblem);
            responseEntity = new ResponseEntity<>(fabricCheckRecordProblem, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[selectById] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new FabricCheckRecordProblem(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 通过验货表id查询统计列表
     *
     * @param recordId 布料记录表id
     * @return
     */
    @ApiOperation(value = "通过验货表id查询统计列表", notes = "通过验货表id查询统计列表")
    @GetMapping(value = "getCountData")
    public ResponseEntity<List<FabricCheckRecordProblem>> getCountData(
            @RequestParam String recordId) {
        ResponseEntity<List<FabricCheckRecordProblem>> responseEntity;
        try {
            FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem();
            fabricCheckRecordProblem.setRecordId(recordId);
            List<FabricCheckRecordProblem> list = fabricCheckRecordProblemService.getCountData(fabricCheckRecordProblem);
            responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[getCountData] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
