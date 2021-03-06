package com.bc.app.server.controller;

import cn.jmessage.api.JMessageClient;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.User;
import com.bc.app.server.entity.UserApply;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.enums.VerifyCodeTypeEnum;
import com.bc.app.server.service.SmsConfigService;
import com.bc.app.server.service.UserApplyService;
import com.bc.app.server.service.UserService;
import com.bc.app.server.service.VerifyCodeService;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.utils.Md5Util;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户申请
 *
 * @author zhou
 */
@RestController
@RequestMapping("/userApply")
public class UserApplyController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

    @Resource
    VerifyCodeService verifyCodeService;

    @Resource
    SmsConfigService smsConfigService;

    @Resource
    UserApplyService userApplyService;

    @Autowired
    UserService userService;

    @Autowired
    JMessageClient jMessageClient;

    /**
     * 提交用户申请
     *
     * @param phone        手机号
     * @param code         验证码
     * @param password     密码
     * @param enterpriseId 企业ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "提交用户申请", notes = "提交用户申请")
    @PostMapping(value = "")
    public ResponseEntity<String> addUserApply(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam String code,
            @RequestParam String password,
            @RequestParam String enterpriseId) {
        ResponseEntity<String> responseEntity;
        try {
            // 检查验证码
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("phone", phone);
            paramMap.put("type", VerifyCodeTypeEnum.REGISTER.getCode());
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

            // 添加用户申请
            UserApply userApply = new UserApply(enterpriseId, name, phone, password);
            userApplyService.addUserApply(userApply);

            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_USER_APPLY_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[addUserApply] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_USER_APPLY_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 操作用户申请
     *
     * @param applyId       申请ID
     * @param operateStatus 操作状态
     * @return ResponseEntity
     */
    @ApiOperation(value = "操作用户申请", notes = "操作用户申请")
    @PutMapping(value = "/{applyId}")
    public ResponseEntity<String> operateUserApply(
            @PathVariable String applyId,
            @RequestParam String operateStatus) {
        ResponseEntity<String> responseEntity;
        try {
            if (Constant.OPERATE_STATUS_AGREE.equals(operateStatus)) {
                // 同意
                Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
                paramMap.put("applyId", applyId);
                UserApply userApply = userApplyService.getUserApplyById(paramMap);
                // 用户表新增数据
                String userId = CommonUtil.generateId();
                String imPassword = Md5Util.md5Decode32(Constant.DEFAULT_PASSWORD);

                User user = new User();
                user.setId(userId);
                user.setEnterpriseId(userApply.getEnterpriseId());
                user.setName(userApply.getName());
                user.setPassword(userApply.getPassword());
                user.setImPassword(imPassword);
                user.setPhone(userApply.getPhone());
                user.setJobNo(CommonUtil.getJobNo());
                userService.addUser(user);

                // 注册到极光
                try {
                    jMessageClient.registerAdmins(userId, imPassword);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("register user error. userId: " + userId);
                }

                // 发送同意短信
            } else {
                // 拒绝
                // 发送拒绝短信
            }
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("applyId", applyId);
            paramMap.put("operateStatus", operateStatus);
            userApplyService.agreeOrRefuseUserApply(paramMap);

            responseEntity = new ResponseEntity<>(ResponseMsg.
                    OPERATE_USER_APPLY_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[operateUserApply] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    OPERATE_USER_APPLY_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获得企业下未处理的申请记录
     *
     * @param enterpriseId 企业ID
     * @return 获得企业下未处理的申请记录列表
     */
    @ApiOperation(value = "获得企业下未处理的申请记录列表", notes = "获得企业下未处理的申请记录列表")
    @GetMapping(value = "")
    public ResponseEntity<List<UserApply>> getUserApplyList(
            @RequestParam String enterpriseId) {
        ResponseEntity<List<UserApply>> responseEntity;
        try {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("operateStatus", Constant.OPERATE_STATUS_INIT);

            List<UserApply> userApplyList = userApplyService.getUserApplyList(paramMap);
            responseEntity = new ResponseEntity<>(userApplyList, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[getUserApplyList] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}