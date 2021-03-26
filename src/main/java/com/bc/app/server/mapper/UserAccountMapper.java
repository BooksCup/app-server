package com.bc.app.server.mapper;


import com.bc.app.server.entity.UserAccount;

import java.util.Map;

/**
 * 实名认证记录
 *
 * @author qiu
 */
public interface UserAccountMapper {

    /**
     * 通过用户id查询个人实名认证记录信息
     *
     * @param map 入参
     * @return 个人实名认证记录信息
     */
    UserAccount getUserAccountByUserId(Map<String, String> map);

    /**
     * 保存个人实名认证记录信息
     *
     * @param userAccount 个人实名认证记录信息
     */
    void insert(UserAccount userAccount);

    /**
     *
     * @param account
     */
    void updateUserAccountByAccountId(UserAccount account);
}
