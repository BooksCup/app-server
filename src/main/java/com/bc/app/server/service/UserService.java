package com.bc.app.server.service;

import com.bc.app.server.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author zhou
 */
public interface UserService {

    /**
     * 通过登录获取用户
     *
     * @param phone    手机号
     * @param password 密码
     * @return 用户
     */
    User getUserByLogin(String phone, String password);

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
     * 查询用户分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 用户分页信息
     */
    PageInfo<User> getUserPageInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

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
