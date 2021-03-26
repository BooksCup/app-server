package com.bc.app.server.controller.econtract;

import com.bc.app.server.entity.EContractApiResult;
import com.bc.app.server.service.RealNameCertService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 实名认证
 *
 * @author zhou
 */
@RestController
@RequestMapping("/realNameCert")
public class RealNameCertController {

    @Resource
    RealNameCertService realNameCertService;

    /**
     * 添加用户账号至e签宝平台并开启实名认证流程
     * 对个人运营商三要素信息进行核验，成功后向手机号发送验证码
     *
     * @param idType      认证证件类型
     *                    CRED_PSN_CH_IDCARD:大陆身份证
     *                    CRED_PSN_CH_TWCARD:台湾来往大陆通行证
     *                    CRED_PSN_CH_MACAO:澳门来往大陆通行证
     *                    CRED_PSN_CH_HONGKONG:香港来往大陆通行证
     *                    CRED_PSN_PASSPORT:护照
     * @param userId      用户ID
     * @param userName    姓名
     * @param identityNum 身份证号码
     * @param mobile      手机号码 使用短信接收签署相关通知，短信验证码做意愿认证
     * @return 实名认证结果
     */
    @ApiOperation(value = "添加用户账号至e签宝平台并开启实名认证流程", notes = "添加用户账号至e签宝平台并开启实名认证流程")
    @PostMapping(value = "")
    public ResponseEntity<EContractApiResult> addUserAccount(
            @RequestParam(required = false, defaultValue = "CRED_PSN_CH_IDCARD") String idType,
            @RequestParam String userId,
            @RequestParam String userName,
            @RequestParam String mobile,
            @RequestParam String identityNum) {
        ResponseEntity<EContractApiResult> responseEntity;
        try {
            EContractApiResult electronContractApi = realNameCertService.addUserAccount(idType, userId, userName, identityNum, mobile);
            responseEntity = new ResponseEntity<>(electronContractApi, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new EContractApiResult(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 校验验证码
     *
     * @param userId 用户ID
     * @param code   验证码
     * @return e签宝平台返回结果
     */
    @ApiOperation(value = "校验验证码", notes = "校验验证码")
    @GetMapping(value = "/check")
    public ResponseEntity<EContractApiResult> checkVerifyCode(
            @RequestParam String userId,
            @RequestParam String code) {
        ResponseEntity<EContractApiResult> responseEntity;
        try {
            EContractApiResult electronContractApi = realNameCertService.checkVerifyCode(userId, code);
            responseEntity = new ResponseEntity<>(electronContractApi, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new EContractApiResult(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
