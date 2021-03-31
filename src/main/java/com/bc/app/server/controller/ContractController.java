package com.bc.app.server.controller;

import com.bc.app.server.entity.Contract;
import com.bc.app.server.service.ContractService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 合同
 *
 * @author zhou
 */
@RestController
@RequestMapping("/contract")
public class ContractController {

    @Autowired
    ContractService contractService;

    /**
     * 获取合同分页信息
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 合同分页信息
     */
    @ApiOperation(value = "获取合同分页信息", notes = "获取合同分页信息")
    @GetMapping(value = "/search")
    public ResponseEntity<PageInfo<Contract>> getContractPageInfo(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String enterpriseId,
            @RequestParam String phone,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<Contract>> responseEntity;
        try {
            PageInfo<Contract> pageInfo = contractService.getContractPageInfo(enterpriseId, phone, keyword, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 根据合同ID获取合同
     *
     * @param contractId 合同ID
     * @return 合同
     */
    @CrossOrigin
    @ApiOperation(value = "根据合同ID获取合同", notes = "根据合同ID获取合同")
    @GetMapping(value = "/{contractId}")
    public ResponseEntity<Contract> getContractById(
            @PathVariable String contractId,
            @RequestParam String enterpriseId) {
        ResponseEntity<Contract> responseEntity;
        try {
            Contract contract = contractService.getContractById(contractId, enterpriseId);
            responseEntity = new ResponseEntity<>(contract, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new Contract(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
