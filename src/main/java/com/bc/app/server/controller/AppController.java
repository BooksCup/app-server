package com.bc.app.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.App;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.AppService;
import com.bc.app.server.service.UserAppService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 应用
 *
 * @author zhou
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @Resource
    AppService appService;

    @Resource
    UserAppService userAppService;

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

    /**
     * 获取应用分页信息
     *
     * @param userId   用户ID
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 应用分页信息
     */
    @ApiOperation(value = "获取应用分页信息", notes = "获取应用分页信息")
    @GetMapping(value = "")
    public ResponseEntity<PageInfo<App>> getAppList(
            @RequestParam String userId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<App>> responseEntity;
        try {
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("userId", userId);
            PageInfo<App> appPageInfo = userAppService.getAppList(paramMap, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(appPageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
