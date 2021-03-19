package com.bc.app.server.service.impl;

import com.bc.app.server.entity.User;
import com.bc.app.server.mapper.SystemRoleConfigMapper;
import com.bc.app.server.service.SystemRoleConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统权限配置
 *
 * @author zhou
 */
@Service("systemRoleConfigService")
public class SystemRoleConfigServiceImpl implements SystemRoleConfigService {

    @Resource
    private SystemRoleConfigMapper systemRoleConfigMapper;

    /**
     * 通过模块和角色获取用户列表
     *
     * @param paramMap 参数map
     * @return 用户列表
     */
    @Override
    public List<User> getUserListByModuleAndRole(Map<String, Object> paramMap) {
        return systemRoleConfigMapper.getUserListByModuleAndRole(paramMap);
    }

}
