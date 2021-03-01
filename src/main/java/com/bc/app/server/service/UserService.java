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
     * 通过登录获取用户列表
     *
     * @param phone    手机号
     * @param password 密码
     * @return 用户列表
     */
    User getUserByLogin(String phone, String password);

    /**
     * 根据用户ID获取用户
     *
     * @param userId 用户ID
     * @return 用户
     */
    List<User> getUserById(String userId);

    /**
     * 查询用户分页信息
     *
     * @param map      入参信息
     * @param pageNum  当前页
     * @param pageSize 每页实现个数
     * @return 用户分页信息
     */
    PageInfo<User> getUserPageInfo(Map<String, String> map, Integer pageNum, Integer pageSize);
}
