package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.App;
import com.bc.app.server.entity.User;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.mapper.UserMapper;
import com.bc.app.server.service.UserAppService;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.vo.appcontrollervo.UserAppVo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


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
    private static final Logger logger = LoggerFactory.getLogger(UserAppController.class);

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
     * 获取个人应用市场所有模块列表
     *
     * @param userId 用户id
     * @return 个人应用市场所有模块列表
     */
    @ApiOperation(value = "获取个人应用市场所有模块", notes = "获取个人应用市场所有模块")
    @GetMapping(value = "/{userId}/app")
    public ResponseEntity<UserAppVo> getAppList(
            @PathVariable String userId,
            @RequestParam(required = false) String enterpriseId) {
        ResponseEntity<UserAppVo> responseEntity;
        try {
            Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            map.put("userId", userId);
            map.put("enterpriseId", enterpriseId);
            UserAppVo userAppVo = userAppService.getAppListByUserId(map);
            responseEntity = new ResponseEntity<>(userAppVo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new UserAppVo(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
