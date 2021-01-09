package com.bc.app.server.mapper;

import com.bc.app.server.entity.VerifyCode;

/**
 * 验证码
 *
 * @author zhou
 */
public interface VerifyCodeMapper {

    /**
     * 保存验证码
     *
     * @param verifyCode 验证码
     */
    void addVerifyCode(VerifyCode verifyCode);

}
