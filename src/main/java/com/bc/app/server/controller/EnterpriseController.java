package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.Enterprise;
import com.bc.app.server.entity.User;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.EnterpriseService;
import io.swagger.annotations.ApiOperation;
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
 * 企业
 *
 * @author zhou
 */
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    @Resource
    private EnterpriseService enterpriseService;

    /**
     * 根据关键字搜索企业列表
     *
     * @param keyword 关键字
     * @return 企业列表
     */
    @ApiOperation(value = "根据关键字搜索企业列表", notes = "根据关键字搜索企业列表")
    @GetMapping(value = "/search")
    public ResponseEntity<List<Enterprise>> getEnterpriseListByKeyword(@RequestParam String keyword) {
        ResponseEntity<List<Enterprise>> responseEntity;
        try {
            if (StringUtils.isEmpty(keyword.trim())) {
                return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
            }
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("keyword", keyword);
            List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByKeyword(paramMap);
            responseEntity = new ResponseEntity<>(enterpriseList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @ApiOperation(value = "修改企业", notes = "修改企业")
    @PutMapping(value = "/{enterpriseId}")
    public ResponseEntity<String> updateEnterprise(
            @PathVariable String enterpriseId,
            @RequestParam String legalPersonName,
            @RequestParam String shortName,
            @RequestParam String regCapital,
            @RequestParam String estiblishDate,
            @RequestParam String regStatus,
            @RequestParam String telephone) {
        ResponseEntity<String> responseEntity;
        try {
            Enterprise enterprise = new Enterprise(enterpriseId, shortName, legalPersonName,
                    estiblishDate, regStatus, regCapital, telephone);
            enterpriseService.updateEnterprise(enterprise);
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_ENTERPRISE_SUCCESS.
                    getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_ENTERPRISE_ERROR.
                    getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @ApiOperation(value = "获取企业下的人员列表", notes = "获取企业下的人员列表")
    @GetMapping(value = "/{enterpriseId}/user")
    public ResponseEntity<List<User>> getEnterpriseUserList(
            @PathVariable String enterpriseId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String userId) {
        ResponseEntity<List<User>> responseEntity;
        try {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("keyword", keyword);

            List<User> reviewerList = enterpriseService.getEnterpriseUserList(paramMap);
            responseEntity = new ResponseEntity<>(reviewerList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
