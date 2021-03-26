package com.bc.app.server.service;

import com.bc.app.server.entity.EContractApiResult;

/**
 * 实名认证
 *
 * @author zhou
 */
public interface RealNameCertService {

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
     * @param identityNum 身份证号
     * @param mobile      手机号
     * @return e签宝平台返回结果
     */
    EContractApiResult addUserAccount(String idType, String userId, String userName, String identityNum, String mobile);

    /**
     * 校验验证码
     *
     * @param userId 用户ID
     * @param code   验证码
     * @return e签宝平台返回结果
     */
    EContractApiResult checkVerifyCode(String userId, String code);

}
