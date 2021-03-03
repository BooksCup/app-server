package com.bc.app.server.mapper;

import com.bc.app.server.entity.App;

import java.util.List;

/**
 * 应用
 *
 * @author zhou
 */
public interface AppMapper {

    /**
     * 添加用户安装应用记录
     *
     * @param appList 安装记录
     */
    void batchAddUserApp(List<App> appList);
}
