package com.bc.app.server.mapper;

import com.bc.app.server.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author zhou
 */
public interface UserMapper {

    /**
     * 通过用户名和密码获取用户列表(用于登录)
     *
     * @param paramMap 参数map
     * @return 用户列表
     */
    List<User> getUserByPhoneAndPassword(Map<String, String> paramMap);

    /**
     * 根据用户ID获取用户
     *
     * @param userId 用户ID
     * @return 用户
     */
    List<User> getUserById(String userId);


    /**
     * 获取用户列表信息
     *
     * @param map 入参
     * @return 用户列表信息
     */
    List<User> getUserList(Map<String, String> map);
}
