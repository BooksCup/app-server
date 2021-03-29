package com.bc.app.server.controller;

import com.bc.app.server.entity.OrgSeal;
import com.bc.app.server.service.OrgSealService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 印章
 *
 * @author zhou
 */
@RestController
@RequestMapping("/orgSeal")
public class OrgSealController {

    @Resource
    private OrgSealService orgSealService;

    /**
     * 获取企业下的机构印章列表
     *
     * @param enterpriseId 企业ID
     * @return 机构印章列表
     */
    @ApiOperation(value = "获取企业下的机构印章列表", notes = "获取企业下的机构印章列表")
    @GetMapping(value = "")
    public ResponseEntity<List<OrgSeal>> getOrgSealListByEnterpriseId(
            @RequestParam String enterpriseId) {
        ResponseEntity<List<OrgSeal>> responseEntity;
        try {
            List<OrgSeal> orgSealList = orgSealService.getOrgSealListByEnterpriseId(enterpriseId);
            responseEntity = new ResponseEntity<>(orgSealList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
