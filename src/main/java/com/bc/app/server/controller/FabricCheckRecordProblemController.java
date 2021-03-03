package com.bc.app.server.controller;


import com.alibaba.fastjson.JSON;
import com.bc.app.server.entity.FabricCheckProblemConfig;
import com.bc.app.server.entity.FabricCheckRecordProblem;
import com.bc.app.server.entity.FabricCheckRecordProblemPosition;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.FabricCheckProblemConfigService;
import com.bc.app.server.service.FabricCheckRecordProblemService;
import com.bc.app.server.vo.fabriccheckrecordproblemcontrollervo.GetFabricQcRecordProblemByRecordIdVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * 面料验货问题配置
 */
@RestController
@RequestMapping("/fabricCheckRecordProblem")
public class FabricCheckRecordProblemController {


    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(FabricCheckRecordProblemController.class);


    @Autowired
    private FabricCheckRecordProblemService fabricCheckRecordProblemService;

    @Autowired
    private FabricCheckProblemConfigService fabricCheckProblemConfigService;

    /**
     * 新增问题记录
     *
     * @param fabricCheckRecordProblemPositionStrJson 记录信息表id
     * @return
     */
    @ApiOperation(value = "新增问题记录", notes = "新增问题记录")
    @PostMapping(value = "")
    public ResponseEntity<FabricCheckRecordProblemPosition> addFabricQcRecordProblem(
            @RequestParam String fabricCheckRecordProblemPositionStrJson) {
        ResponseEntity<FabricCheckRecordProblemPosition> responseEntity;
        try {
            FabricCheckRecordProblemPosition fabricCheckRecordProblemPosition = JSON.parseObject(fabricCheckRecordProblemPositionStrJson, FabricCheckRecordProblemPosition.class);
            List<FabricCheckRecordProblem> fabricCheckRecordProblemList = JSON.parseArray(fabricCheckRecordProblemPosition.getFabricCheckRecordProblemStrJson(), FabricCheckRecordProblem.class);
            fabricCheckRecordProblemPosition.setFabricCheckRecordProblemList(fabricCheckRecordProblemList);
            //新增之前查询一下数据
//          List<FabricCheckRecordProblemPosition> fcrbpList=  fabricCheckRecordProblemPositionService.selectByRecordIdAndPosition(fabricCheckRecordProblemPosition);
//            if (!CollectionUtils.isEmpty(fcrbpList)){
//                return new ResponseEntity<>(new FabricCheckRecordProblemPosition(), HttpStatus.INTERNAL_SERVER_ERROR);
//            }
            fabricCheckRecordProblemService.addFabricQcRecordProblem(fabricCheckRecordProblemPosition);
            responseEntity = new ResponseEntity<>(fabricCheckRecordProblemPosition, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[addFabricQcProblemConfig] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new FabricCheckRecordProblemPosition(), HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<GetFabricQcRecordProblemByRecordIdVo> getFabricQcRecordProblemByRecordId(
            @RequestParam String recordId,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String enterpriseId) {
        ResponseEntity<GetFabricQcRecordProblemByRecordIdVo> responseEntity;
        try {
            FabricCheckRecordProblem fabricCheckRecordProblem = new FabricCheckRecordProblem();
            fabricCheckRecordProblem.setRecordId(recordId);
            fabricCheckRecordProblem.setTag(tag);
            GetFabricQcRecordProblemByRecordIdVo g = new GetFabricQcRecordProblemByRecordIdVo();
            List<FabricCheckRecordProblemPosition> list = fabricCheckRecordProblemService.getFabricQcRecordProblemByRecordId(fabricCheckRecordProblem);
            g.setFabricCheckRecordProblemPositionList(list);
            if (!StringUtils.isEmpty(enterpriseId)) {
                FabricCheckProblemConfig fabricCheckProblemConfig = new FabricCheckProblemConfig();
                fabricCheckProblemConfig.setEnterpriseId(enterpriseId);
                List<FabricCheckProblemConfig> fabricCheckProblemConfigList = fabricCheckProblemConfigService.getAlllByEnterpriseId(fabricCheckProblemConfig);
                g.setFabricCheckProblemConfigList(fabricCheckProblemConfigList);
            }
            responseEntity = new ResponseEntity<>(g, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[getFabricQcRecordProblemByRecordId] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new GetFabricQcRecordProblemByRecordIdVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 通过记录表id进行删除数据
     *
     * @param ids 问题记录表ids
     * @return
     */
    @ApiOperation(value = "通过记录表id进行删除数据", notes = "通过记录表id进行删除数据")
    @PutMapping(value = "/deleteByIds")
    public ResponseEntity<String> delete(
            @RequestParam String ids) {
        ResponseEntity<String> responseEntity;
        try {
            List<FabricCheckRecordProblem> fabricCheckRecordProblemList = JSON.parseArray(ids, FabricCheckRecordProblem.class);
            fabricCheckRecordProblemService.updateByIds(fabricCheckRecordProblemList);
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_SUCCESS.getResponseMessage(), HttpStatus.OK);
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
     * @param fabricCheckRecordProblemListStrJson 记录信息表对象信息
     * @return
     */
    @ApiOperation(value = "通过id更新数据", notes = "通过id更新数据")
    @PutMapping(value = "/batchUpdateRecordProblems")
    public ResponseEntity<String> batchUpdateRecordProblems(
            @RequestParam String fabricCheckRecordProblemListStrJson) {
        ResponseEntity<String> responseEntity;
        try {
            List<FabricCheckRecordProblem> fabricCheckRecordProblemList = JSON.parseArray(fabricCheckRecordProblemListStrJson, FabricCheckRecordProblem.class);
            fabricCheckRecordProblemService.updateRecordProblemById(fabricCheckRecordProblemList);
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
