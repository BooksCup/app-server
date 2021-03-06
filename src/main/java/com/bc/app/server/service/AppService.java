package com.bc.app.server.service;

import com.bc.app.server.entity.App;

import java.util.List;

/**
 * 应用
 *
 * @author zhou
 */

public interface AppService {

    /**
     * 重置已经安装的应用列表
     *
     * @param appList 应用列表
     */
    void resetUserApp(List<App> appList,String userId);
}
