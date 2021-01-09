package com.bc.app.server.service;

import com.bc.app.server.entity.VerifyCode;

/**
 * 验证码
 *
 * @author zhou
 */
public interface VerifyCodeService {

    /**
     * 保存验证码
     *
     * @param verifyCode 验证码
     */
    void addVerifyCode(VerifyCode verifyCode);

}
