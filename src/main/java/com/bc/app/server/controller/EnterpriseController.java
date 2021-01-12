package com.bc.app.server.controller;

import com.bc.app.server.entity.Enterprise;
import com.bc.app.server.service.EnterpriseService;
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
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("keyword", keyword);
            List<Enterprise> enterpriseList = enterpriseService.getEnterpriseListByKeyword(paramMap);
            responseEntity = new ResponseEntity<>(enterpriseList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
