package com.bc.app.server.controller;

import com.bc.app.server.entity.User;
import com.bc.app.server.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
        logger.info("[login] phone: " + phone);
        List<User> userList = userService.getUserByLogin(phone, password);

        if (CollectionUtils.isEmpty(userList)) {
            responseEntity = new ResponseEntity<>(new User(),
                    HttpStatus.BAD_REQUEST);
        } else {
            User user = userList.get(0);
            responseEntity = new ResponseEntity<>(user,
                    HttpStatus.OK);

            // 修改最后一次登录时间
//            userService.updateUserLastLoginTime(user.getUserId());
        }

        return responseEntity;
    }
}
