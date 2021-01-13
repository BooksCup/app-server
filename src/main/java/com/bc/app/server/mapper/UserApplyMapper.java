package com.bc.app.server.mapper;

import com.bc.app.server.entity.UserApply;

/**
 * 用户申请
 *
 * @author zhou
 */
public interface UserApplyMapper {

    /**
     * 添加用户申请
     *
     * @param userApply 用户申请
     */
    void addUserApply(UserApply userApply);

}
