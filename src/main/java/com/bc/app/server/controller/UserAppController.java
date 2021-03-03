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

    @ApiOperation(value = "获取应用列表", notes = "获取应用列表")
    @GetMapping(value = "")
    public ResponseEntity<List<App>> getAppList(
            @RequestParam String userId) {
        ResponseEntity<List<App>> responseEntity;
        try {
            Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            map.put("userId", userId);
            List<App> appList = userAppService.getAppList(map);
            responseEntity = new ResponseEntity<>(appList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
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


    @Autowired
    UserMapper userMapper;

    @GetMapping(value = "/pushData")
    public ResponseEntity<String> pushData() {
        ResponseEntity<String> responseEntity;

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("27f0b843ea4f495bbdd522105d7ea8cb");
        linkedList.add("c810bf2ca95041cda1e1e6b0c79202fc");
        linkedList.add("db130f330117470792d3f0b97efef756");
        linkedList.add("f27660fabd1c42a6b29bc8a75cf4add0");
        linkedList.add("44c5f689923f43a6a684d063f7c58b17");
        linkedList.add("900f4c4148ec4cd6b5508f3bea41819b");
        linkedList.add("e3c5f3b1152a465f990a79566834b053");
        linkedList.add("b52c207497564d9e8e19400d988bdf51");
        linkedList.add("56e58a07ba42498e8e9f905c9fc31796");
        linkedList.add("37f594deed94424daceeaacdd4c1172b");
        linkedList.add("e2373ee7add249c48e4152527332c6ec");
        linkedList.add("0a646fb914914b82b46765d1f74cee8a");
        linkedList.add("1a9c99352f5340439926bac6e92abcfd");
        linkedList.add("e7e7e91462ed44e99013b707a5705f1e");
        linkedList.add("50be1bff1052410383ff484a0f2c0b5d");
        linkedList.add("f20107035f7b40e38305711ed4422cdb");

        linkedList.add("34462553fb4e4f60b1d9b45ae99347a5");
        linkedList.add("fcf39169fb944d67b8ed93345eb0141c");

        Map<String,String> map = new HashMap<>();
        map.put("enterpriseId","1");
        LinkedList<App> appList = new LinkedList<>();
        for (int i = 0; i < linkedList.size(); i++) {
            App app = new App();
            app.setUaId(CommonUtil.generateId());
            app.setUserId("e88a74d67d974bd5916c68c4f3acc3ed");
            app.setUaAppId(linkedList.get(i));
            app.setUaSort((i+1)+"");
            if ("34462553fb4e4f60b1d9b45ae99347a5".equals(linkedList.get(i)) ||"fcf39169fb944d67b8ed93345eb0141c".equals(linkedList.get(i))) {
                app.setIsFast("1");
            }else {
                app.setIsFast("0");
            }
            appList.add(app);
        }
        userMapper.addUserAppList(appList);

        return null;
    }
}
