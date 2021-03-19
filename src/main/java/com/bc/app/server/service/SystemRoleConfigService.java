package com.bc.app.server.service;

import com.bc.app.server.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 系统权限配置
 *
 * @author zhou
 */
public interface SystemRoleConfigService {

    /**
     * 通过模块和角色获取用户列表
     *
     * @param paramMap 参数map
     * @return 用户列表
     */
    List<User> getUserListByModuleAndRole(Map<String, Object> paramMap);

}
