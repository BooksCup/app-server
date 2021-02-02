package com.bc.app.server.controller;


import com.bc.app.server.entity.FabricQcProblemConfig;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.FabricQcProblemConfigService;
import com.bc.app.server.utils.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
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
@RequestMapping("/fabricQcProblemConfig")
public class FabricQcProblemConfigController {


    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);


    @Autowired
    private FabricQcProblemConfigService fabricQcProblemConfigService;

    /**
     * 新增问题配置信息
     *
     * @param enterpriseId 企业id
     * @param tag
     * @return
     */
    @ApiOperation(value = "新增问题配置信息", notes = "新增问题配置信息")
    @PostMapping(value = "")
    public ResponseEntity<String> addFabricQcProblemConfig(
            @RequestParam String enterpriseId,
            @RequestParam String tag) {
        ResponseEntity<String> responseEntity;
        try {
            FabricQcProblemConfig fabricQcProblemConfig = new FabricQcProblemConfig();
            fabricQcProblemConfig.setEnterpriseId(enterpriseId);
            fabricQcProblemConfig.setTag(tag);
            //去重判断
            List<FabricQcProblemConfig> alllByEnterpriseIds = fabricQcProblemConfigService.getAlllByEnterpriseId(fabricQcProblemConfig);
            if (CollectionUtils.isNotEmpty(alllByEnterpriseIds)) {
                responseEntity = new ResponseEntity<>(ResponseMsg.ADD_REPEAT.getResponseMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                return responseEntity;
            }
            fabricQcProblemConfig.setId(CommonUtil.generateId());
            fabricQcProblemConfigService.addFabricQcProblemConfig(fabricQcProblemConfig);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseMessage(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[addFabricQcProblemConfig] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 通过企业id查询问题配置
     *
     * @param enterpriseId 企业id
     * @return
     */
    @ApiOperation(value = "通过企业id查询问题配置列表", notes = "通过企业id查询问题配置列表")
    @GetMapping(value = "")
    public ResponseEntity<List<FabricQcProblemConfig>> getAlllByEnterpriseId(
            @RequestParam String enterpriseId) {
        ResponseEntity<List<FabricQcProblemConfig>> responseEntity;
        try {
            FabricQcProblemConfig fabricQcProblemConfig = new FabricQcProblemConfig();
            fabricQcProblemConfig.setEnterpriseId(enterpriseId);
            List<FabricQcProblemConfig> alllByEnterpriseId = fabricQcProblemConfigService.getAlllByEnterpriseId(fabricQcProblemConfig);
            responseEntity = new ResponseEntity<>(alllByEnterpriseId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[getAlllByEnterpriseId] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
