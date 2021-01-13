package com.bc.app.server.service.impl;

import com.bc.app.server.entity.VerifyCode;
import com.bc.app.server.mapper.VerifyCodeMapper;
import com.bc.app.server.service.VerifyCodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 验证码
 *
 * @author zhou
 */
@Service("verifyCodeService")
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Resource
    private VerifyCodeMapper verifyCodeMapper;

    /**
     * 保存验证码
     *
     * @param verifyCode 验证码
     */
    @Override
    public void addVerifyCode(VerifyCode verifyCode) {
        verifyCodeMapper.addVerifyCode(verifyCode);
    }

    /**
     * 获取有效验证码列表
     *
     * @param paramMap 参数map
     * @return 有效验证码列表
     */
    @Override
    public List<String> getValidVerifyCodeList(Map<String, String> paramMap) {
        return verifyCodeMapper.getValidVerifyCodeList(paramMap);
    }

}
