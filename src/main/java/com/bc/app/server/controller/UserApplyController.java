package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.UserApply;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.SmsConfigService;
import com.bc.app.server.service.UserApplyService;
import com.bc.app.server.service.VerifyCodeService;
import com.bc.app.server.utils.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    private VerifyCodeService verifyCodeService;

    @Resource
    private SmsConfigService smsConfigService;

    @Resource
    private UserApplyService userApplyService;


    @ApiOperation(value = "提交用户申请", notes = "提交用户申请")
    @PostMapping(value = "")
    public ResponseEntity<String> addUserApply(
            @RequestParam String phone,
            @RequestParam String code,
            @RequestParam String password,
            @RequestParam String enterpriseId) {
        ResponseEntity<String> responseEntity;
        try {
            // 检查验证码
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("phone", phone);
            paramMap.put("type", Constant.VERIFY_CODE_TYPE_REGISTER);
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
            UserApply userApply = new UserApply();
            userApply.setId(CommonUtil.generateId());
            userApply.setEnterpriseId(enterpriseId);
            userApply.setPhone(phone);
            userApply.setPassword(password);
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

}
