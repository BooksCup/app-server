package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.User;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.enums.VerifyCodeTypeEnum;
import com.bc.app.server.service.UserApplyService;
import com.bc.app.server.service.UserService;
import com.bc.app.server.service.VerifyCodeService;
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

    @Resource
    private UserApplyService userApplyService;

    @Resource
    private VerifyCodeService verifyCodeService;

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
            List<User> userListByPhone = userService.getUserByPhone(phone);
            if (CollectionUtils.isEmpty(userListByPhone)) {
                // 用户不存在
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                headers.add("responseCode", ResponseMsg.USER_NOT_EXIST.getResponseCode());
                headers.add("responseMessage", ResponseMsg.USER_NOT_EXIST.getResponseMessage());
                return new ResponseEntity<>(new User(), headers, HttpStatus.BAD_REQUEST);
            }

            User user = userService.getUserByLogin(phone, password);
            if (null == user) {
                // 密码错误
                MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
                headers.add("responseCode", ResponseMsg.PASSWORD_INCORRECT.getResponseCode());
                headers.add("responseMessage", ResponseMsg.PASSWORD_INCORRECT.getResponseMessage());
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
     * @param pageNum      当前分页数
     * @param pageSize     分页大小
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
        Map<String, Object> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
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

    /**
     * 修改用户密码
     *
     * @param phone    手机号
     * @param code     验证码
     * @param password 密码
     * @return ResponseEntity
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping(value = "/password")
    public ResponseEntity<String> modifyUserPassword(
            @RequestParam String phone,
            @RequestParam String code,
            @RequestParam String password) {
        ResponseEntity<String> responseEntity;
        try {
            // 检查验证码
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("phone", phone);
            paramMap.put("type", VerifyCodeTypeEnum.MODIFY_PASSWORD.getCode());
            List<String> validVerifyCodeList = verifyCodeService.getValidVerifyCodeList(paramMap);
            if (CollectionUtils.isEmpty(validVerifyCodeList)) {
                return new ResponseEntity<>(ResponseMsg.
                        VERIFY_CODE_EXPIRE.getResponseCode(), HttpStatus.BAD_REQUEST);
            } else {
                String validVerifyCode = validVerifyCodeList.get(0);
                if (!code.equals(validVerifyCode)) {
                    return new ResponseEntity<>(ResponseMsg.
                            VERIFY_CODE_EXPIRE.getResponseCode(), HttpStatus.BAD_REQUEST);
                }
            }

            // 修改密码
            paramMap.clear();
            paramMap.put("password", password);
            paramMap.put("phone", phone);
            userService.updateUserPwd(paramMap);

            responseEntity = new ResponseEntity<>(ResponseMsg.
                    UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[modifyUserPassword] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
