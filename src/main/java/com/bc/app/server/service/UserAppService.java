package com.bc.app.server.service;

import com.bc.app.server.entity.App;

import java.util.List;

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
     * 获取应用列表
     *
     * @param userId 用户ID
     * @return 应用列表
     */
    List<App> getAppList(String userId);

}
