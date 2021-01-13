package com.bc.app.server.service.impl;

import com.bc.app.server.entity.UserApply;
import com.bc.app.server.mapper.UserApplyMapper;
import com.bc.app.server.service.UserApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户申请
 *
 * @author zhou
 */
@Service("userApplyCodeService")
public class UserApplyServiceImpl implements UserApplyService {

    @Resource
    private UserApplyMapper userApplyMapper;

    /**
     * 添加用户申请
     *
     * @param userApply 用户申请
     */
    @Override
    public void addUserApply(UserApply userApply) {
        userApplyMapper.addUserApply(userApply);
    }

}
