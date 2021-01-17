package com.bc.app.server.service;

import com.bc.app.server.entity.User;

import java.util.List;

/**
 * 用户
 *
 * @author zhou
 */
public interface UserService {

    /**
     * 通过登录获取用户列表
     *
     * @param phone    手机号
     * @param password 密码
     * @return 用户列表
     */
    List<User> getUserByLogin(String phone, String password);

    /**
     * 根据用户ID获取用户
     *
     * @param userId 用户ID
     * @return 用户
     */
    List<User> getUserById(String userId);

}
