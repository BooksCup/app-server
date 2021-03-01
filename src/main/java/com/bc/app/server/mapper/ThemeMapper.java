package com.bc.app.server.mapper;


import com.bc.app.server.entity.Theme;


/**
 * 主题程序
 *
 * @author qiu
 */
public interface ThemeMapper {

    /**
     * 保存主题信息
     *
     * @param theme 主题信息
     */
    void saveTheme(Theme theme);

}
