package com.bc.app.server.mapper;

import com.bc.app.server.entity.VerifyCode;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取有效验证码列表
     *
     * @param paramMap 参数map
     * @return 有效验证码列表
     */
    List<String> getValidVerifyCodeList(Map<String, String> paramMap);

}
