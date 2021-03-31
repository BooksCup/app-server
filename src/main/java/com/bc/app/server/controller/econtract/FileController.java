package com.bc.app.server.controller.econtract;


import com.bc.app.server.service.ElectronicContractService;
import com.bc.app.server.service.FileService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 电子合同
 *
 * @author zhou
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    FileService fileService;

    @ApiOperation(value = "获取合同分页信息", notes = "获取合同分页信息")
    @PostMapping(value = "/signFlow")
    public ResponseEntity<String> createSignFlow(
            @RequestParam String orgId,
            @RequestParam String url
    ) {
        ResponseEntity<String> responseEntity;
        try {
            fileService.getUploadUrl(orgId, url);
            responseEntity = new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
