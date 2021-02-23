package com.bc.app.server.mapper;


import java.util.Map;

/**
 * 主题程序
 *
 * @author qiu
 */
public interface ThemeMapper {

    /**
     * 保存主题信息
     *
     * @param map
     */
    void saveTheme(Map<String, String> map);

}
