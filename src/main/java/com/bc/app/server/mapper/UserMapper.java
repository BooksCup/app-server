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
     * 根据用户ID获取用户列表
     *
     * @param userId 用户ID
     * @return 用户列表
     */
    List<User> getUserById(String userId);

    /**
     * 根据手机号获取用户列表
     *
     * @param phone 手机号
     * @return 用户列表
     */
    List<User> getUserByPhone(String phone);

    /**
     * 查询用户列表
     *
     * @param paramMap 参数map
     * @return 用户列表
     */
    List<User> getUserList(Map<String, Object> paramMap);

    /**
     * 保存用户列表
     *
     * @param userList 用户列表
     */
    void addUserList(List<User> userList);

    /**
     * 保存用户
     *
     * @param user 用户
     */
    void addUser(User user);
}
