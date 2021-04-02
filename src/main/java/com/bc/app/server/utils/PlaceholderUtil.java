package com.bc.app.server.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.Map;

/**
 * 占位符工具类
 *
 * @author zhou
 */
public class PlaceholderUtil {

    /**
     * 使用数据替换模板中的占位符
     *
     * @param template 模板
     * @param dataMap  数据map
     * @return 替换后的完整字符串
     */
    public static String replace(String template, Map<String, String> dataMap) {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(dataMap);
        return stringSubstitutor.replace(template);
    }

}
