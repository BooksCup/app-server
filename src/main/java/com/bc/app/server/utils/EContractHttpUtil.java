package com.bc.app.server.utils;

import com.alibaba.fastjson.JSONObject;
import com.bc.app.server.entity.EContractApiResult;
import org.apache.http.client.methods.HttpHead;
import org.springframework.util.StringUtils;

/**
 * 电子合同网络请求工具类
 *
 * @author zhou
 */
public class EContractHttpUtil {

    private static final String appId = "5111598374";

    public static EContractApiResult httpPost(String url, JSONObject json) {
        String result = null;
        try {
            String token = CommonUtil.getToken();
            if (!StringUtils.isEmpty(token)) {
                HttpHead httpHead = new HttpHead();
                httpHead.setHeader("X-Tsign-Open-App-Id", appId);
                httpHead.setHeader("X-Tsign-Open-Token", token);
                httpHead.setHeader("Content-Type", "application/json");
                result = HttpHelper.httpPost(url, httpHead.getAllHeaders(), String.valueOf(json));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result, EContractApiResult.class);
    }

    public static EContractApiResult httpPut(String url, JSONObject json) {
        String result = null;
        try {
            String token = CommonUtil.getToken();
            if (!StringUtils.isEmpty(token)) {
                HttpHead httpHead = new HttpHead();
                httpHead.addHeader("content-type", "application/json; charset=utf-8");
                httpHead.setHeader("X-Tsign-Open-App-Id", appId);
                httpHead.setHeader("X-Tsign-Open-Token", token);
                result = HttpHelper.httpPut(url, httpHead.getAllHeaders(), String.valueOf(json));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result, EContractApiResult.class);
    }
}
