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
     * 获取应用列表
     *
     * @return 应用列表
     */
    List<App> getAppList();

}
