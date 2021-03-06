package com.bc.app.server.mapper;

import com.bc.app.server.entity.UserApply;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取申请列表
     *
     * @param paramMap 参数map
     * @return 申请列表
     */
    List<UserApply> getUserApplyList(Map<String, Object> paramMap);

    /**
     * 同意或拒绝用户申请
     *
     * @param paramMap 参数map
     */
    void agreeOrRefuseUserApply(Map<String, Object> paramMap);

    /**
     * 通过id查询申诉列表信息
     *
     * @param paramMap 入参
     * @return 申诉列表单个信息
     */
    UserApply getUserApplyById(Map<String, Object> paramMap);
}
