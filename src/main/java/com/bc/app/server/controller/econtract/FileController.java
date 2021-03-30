package com.bc.app.server.controller.econtract;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.FileService;
import com.bc.app.server.utils.PlaceholderUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 上传文件至e签宝
     *
     * @param contractId 合同ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "上传文件至e签宝", notes = "上传文件至e签宝")
    @PostMapping(value = "/upload")
    public ResponseEntity<String> uploadFileToSignPlatform(
            @RequestParam String contractId) {
        ResponseEntity<String> responseEntity;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/contract.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer templateBuffer = new StringBuffer();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                templateBuffer.append(data);
            }

            String template = templateBuffer.toString();
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            String htmlContent = PlaceholderUtil.replace(template, paramMap);
            String fileId = fileService.uploadFileToSignPlatform(htmlContent);
            responseEntity = new ResponseEntity<>(fileId, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.UPLOAD_ERROR.getResponseCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
