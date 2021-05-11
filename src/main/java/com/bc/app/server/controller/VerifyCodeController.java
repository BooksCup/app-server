package com.bc.app.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.SmsConfig;
import com.bc.app.server.entity.VerifyCode;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.enums.VerifyCodeTypeEnum;
import com.bc.app.server.service.SmsConfigService;
import com.bc.app.server.service.VerifyCodeService;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.utils.HttpUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证码
 *
 * @author zhou
 */
@RestController
@RequestMapping("/verifyCode")
public class VerifyCodeController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

    @Resource
    VerifyCodeService verifyCodeService;

    @Resource
    SmsConfigService smsConfigService;

    /**
     * 获取验证码
     *
     * @param phone 手机号
     * @param type  验证码类型
     * @return ResponseEntity<String>
     */
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @PostMapping(value = "")
    public ResponseEntity<String> addVerifyCode(
            @RequestParam String phone,
            @RequestParam String type) {
        ResponseEntity<String> responseEntity;
        String code = CommonUtil.generateRandomNum(6);
        try {
            VerifyCode verifyCode = new VerifyCode(phone, code, type, 5 * 60);
            verifyCodeService.addVerifyCode(verifyCode);

            SmsConfig smsConfig = smsConfigService.getSmsConfigByType(type);
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("phones", phone);
            paramMap.put("signName", smsConfig.getSign());
            paramMap.put("templateCode", smsConfig.getTemplateCode());
            if (type.equals(VerifyCodeTypeEnum.REGISTER.getCode())) {
                // 注册
                Map<String, String> templateParamMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
                templateParamMap.put("code", code);
                paramMap.put("templateParam", JSON.toJSONString(templateParamMap));
                HttpUtil.doPost(Constant.SERVICE_SMS_URL, paramMap);
            } else if (type.equals(VerifyCodeTypeEnum.MODIFY_PASSWORD.getCode())) {
                // 修改密码
                Map<String, String> templateParamMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
                templateParamMap.put("code", code);
                paramMap.put("templateParam", JSON.toJSONString(templateParamMap));
                HttpUtil.doPost(Constant.SERVICE_SMS_URL, paramMap);
            }
            responseEntity = new ResponseEntity<>(ResponseMsg.GET_VERIFY_CODE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[addVerifyCode] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.GET_VERIFY_CODE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
