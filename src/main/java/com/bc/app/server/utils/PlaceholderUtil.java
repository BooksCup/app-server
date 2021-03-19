package com.bc.app.server.utils;

import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

public class PlaceholderUtil {
    public static void main(String[] args) {
        String template = "${userName}提交了新的${stockBizType}单，${goodsName}共${applyNum}${goodsUnit}，点击查看详情";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("userName", "张三");
        paramMap.put("stockBizType", "办公用品入库");
        paramMap.put("goodsName", "笔");
        paramMap.put("applyNum", "13");
        paramMap.put("goodsUnit", "根");
        String result = replace(template, paramMap);
        System.out.println(result);
    }

    public static String replace(String template, Map<String, String> paramMap) {
        StringSubstitutor stringSubstitutor = new StringSubstitutor(paramMap);
        String result = stringSubstitutor.replace(template);
        return result;
    }
}
