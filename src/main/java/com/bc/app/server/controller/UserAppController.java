package com.bc.app.server.controller;

import com.bc.app.server.entity.App;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.UserAppService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户应用程序
 *
 * @author zhou
 */
@RestController
@RequestMapping("/user")
public class UserAppController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

    @Resource
    private UserAppService userAppService;

    /**
     * 重置用户APP
     *
     * @param userId 用户ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "重置用户APP", notes = "重置用户APP")
    @PutMapping(value = "/{userId}/app/reset")
    public ResponseEntity<String> resetUserApp(
            @PathVariable String userId) {
        ResponseEntity<String> responseEntity;
        try {
            userAppService.resetUserApp(userId);
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    RESET_USER_APP_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[resetUserApp] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    RESET_USER_APP_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取用户应用列表
     *
     * @param userId 用户ID
     * @return 用户应用列表
     */
    @ApiOperation(value = "获取用户应用列表", notes = "获取用户应用列表")
    @GetMapping(value = "/{userId}/app")
    public ResponseEntity<List<App>> getAppList(
            @PathVariable String userId) {
        ResponseEntity<List<App>> responseEntity;
        try {
            List<App> appList = userAppService.getAppList(userId);
            responseEntity = new ResponseEntity<>(appList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[getAppList] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
