package com.bc.app.server.mapper;

import com.bc.app.server.entity.App;
import com.bc.app.server.entity.UserApp;

import java.util.List;

/**
 * 应用程序
 *
 * @author zhou
 */
public interface UserAppMapper {

    /**
     * 根据用户ID删除应用程序
     *
     * @param userId 用户ID
     */
    void deleteAppByUserId(String userId);

    /**
     * 为用户批量新增应用程序
     *
     * @param userAppList 用户应用程序列表
     */
    void batchAddUserApp(List<UserApp> userAppList);

    /**
     * 获取默认安装应用程序列表
     *
     * @return 默认安装应用程序列表
     */
    List<App> getDefaultInstallAppList();

    /**
     * 获取应用列表
     *
     * @param userId 用户ID
     * @return 应用列表
     */
    List<App> getAppList(String userId);

}
