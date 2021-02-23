package com.bc.app.server.controller;


import com.bc.app.server.entity.RelatedCompany;
import com.bc.app.server.service.RelatedCompanyService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * 往来单位
 *
 * @author qiu
 */

@RestController
@RequestMapping("/relatedCompany")
public class RelatedCompanyController {

    @Autowired
    RelatedCompanyService relatedCompanyService;

    /**
     * 获取往来单位列表及其账户
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 往来单位列表及其账户
     */
    @ApiOperation(value = "获取往来单位列表及其账户", notes = "获取往来单位列表及其账户")
    @GetMapping(value = "/search")
    public ResponseEntity<PageInfo<RelatedCompany>> getRelatedCompanyPageInfo(
            @RequestParam String enterpriseId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<RelatedCompany>> responseEntity;
        try {
            PageInfo<RelatedCompany> pageInfo = relatedCompanyService.getRelatedCompanyPageInfo(enterpriseId, keyword, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
