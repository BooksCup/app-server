package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.User;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author zhou
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    /**
     * 登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return ResponseEntity
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping(value = "/login")
    public ResponseEntity<User> login(
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String password) {
        ResponseEntity<User> responseEntity;
        try {
            User user = userService.getUserByLogin(phone, password);
            if (null == user) {
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                headers.add("responseCode", ResponseMsg.USER_NOT_EXIST.getResponseCode());
                headers.add("responseMessage", ResponseMsg.USER_NOT_EXIST.getResponseMessage());
                return new ResponseEntity<>(new User(), headers, HttpStatus.BAD_REQUEST);
            } else {
                responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new User(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 根据用户ID获取用户
     *
     * @param userId 用户ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "根据用户ID获取用户", notes = "根据用户ID获取用户")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> getUserById(
            @PathVariable String userId) {
        ResponseEntity<User> responseEntity;
        logger.info("[getUserById] userId: " + userId);
        List<User> userList = userService.getUserById(userId);

        if (CollectionUtils.isEmpty(userList)) {
            responseEntity = new ResponseEntity<>(new User(),
                    HttpStatus.BAD_REQUEST);
        } else {
            User user = userList.get(0);
            responseEntity = new ResponseEntity<>(user,
                    HttpStatus.OK);
        }

        return responseEntity;
    }


    /**
     * 获取用户分页信息
     *
     * @param enterpriseId 企业id
     * @return 用户分页信息
     */
    @ApiOperation(value = "获取用户分页信息", notes = "获取用户分页信息")
    @GetMapping(value = "/search")
    public ResponseEntity<PageInfo<User>> getUserPageInfo(
            @RequestParam String enterpriseId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        logger.info("[getUserPageInfo] pageNum: " + pageNum + ", pageSize: " + pageSize);
        ResponseEntity<PageInfo<User>> responseEntity;
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("enterpriseId", enterpriseId);
        try {
            PageInfo<User> pageInfo = userService.getUserPageInfo(map, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
