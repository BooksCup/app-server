package com.bc.app.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.entity.App;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.AppService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 应用
 *
 * @author zhou
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Resource
    private AppService appService;

    /**
     * 重置用户APP
     *
     * @param userId 用户ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "重置用户APP", notes = "重置用户APP")
    @PutMapping(value = "/{userId}/app/reset")
    public ResponseEntity<String> resetUserApp(
            @PathVariable String userId,
            @RequestParam String appStrJson) {
        ResponseEntity<String> responseEntity;
        try {
            List<App> appList = JSON.parseArray(appStrJson, App.class);
            appService.resetUserApp(appList, userId);
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    RESET_USER_APP_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    RESET_USER_APP_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
