package com.bc.app.server.service;

import com.bc.app.server.entity.App;
import com.bc.app.server.vo.appcontrollervo.UserAppVo;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 用户应用程序
 *
 * @author zhou
 */
public interface UserAppService {

    /**
     * 重置用户应用程序
     *
     * @param userId 用户ID
     */
    void resetUserApp(String userId);

    /**
     * 获取应用分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 应用分页信息
     */
    PageInfo<App> getAppList(Map<String, String> paramMap, Integer pageNum, Integer pageSize);

    /**
     * 获取个人应用市场所有模块
     *
     * @param map 入参
     * @return 个人应用市场所有模块
     */
    UserAppVo getAppListByUserId(Map<String, String> map);
}
