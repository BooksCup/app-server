package com.bc.app.server.service.impl;

import com.bc.app.server.entity.UserApply;
import com.bc.app.server.mapper.UserApplyMapper;
import com.bc.app.server.service.UserApplyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    /**
     * 获取申请列表
     *
     * @param paramMap 参数map
     * @return 申请列表
     */
    @Override
    public List<UserApply> getUserApplyList(Map<String, Object> paramMap) {
        return userApplyMapper.getUserApplyList(paramMap);
    }

    /**
     * 同意或拒绝用户申请
     *
     * @param paramMap 参数map
     */
    @Override
    public void agreeOrRefuseUserApply(Map<String, Object> paramMap) {
        userApplyMapper.agreeOrRefuseUserApply(paramMap);
    }

}
