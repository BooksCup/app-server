package com.bc.app.server.utils;

import com.alibaba.fastjson.JSONObject;
import com.bc.app.server.entity.ElectronicContractApiResult;
import org.apache.http.client.methods.HttpHead;
import org.springframework.util.StringUtils;

/**
 * 电子合同网络请求工具类
 *
 * @author zhou
 */
public class ElectronicContractHttpUtil {

    private static final String appId = "5111598374";

    /**
     * post请求
     *
     * @param url       url
     * @param jsonParam 请求参数(json格式)
     * @return 电子合同API返回值
     */
    public static ElectronicContractApiResult httpPost(String url, String jsonParam) {
        String result = null;
        try {
            String token = CommonUtil.getToken();
            if (!StringUtils.isEmpty(token)) {
                HttpHead httpHead = new HttpHead();
                httpHead.setHeader("X-Tsign-Open-App-Id", appId);
                httpHead.setHeader("X-Tsign-Open-Token", token);
                httpHead.setHeader("Content-Type", "application/json");
                result = HttpHelper.httpPost(url, httpHead.getAllHeaders(), jsonParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result, ElectronicContractApiResult.class);
    }

    /**
     * put请求
     *
     * @param url       url
     * @param jsonParam 请求参数(json格式)
     * @return 电子合同API返回值
     */
    public static ElectronicContractApiResult httpPut(String url, String jsonParam) {
        String result = null;
        try {
            String token = CommonUtil.getToken();
            if (!StringUtils.isEmpty(token)) {
                HttpHead httpHead = new HttpHead();
                httpHead.addHeader("content-type", "application/json; charset=utf-8");
                httpHead.setHeader("X-Tsign-Open-App-Id", appId);
                httpHead.setHeader("X-Tsign-Open-Token", token);
                result = HttpHelper.httpPut(url, httpHead.getAllHeaders(), String.valueOf(jsonParam));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result, ElectronicContractApiResult.class);
    }

    /**
     * 发送字节流
     *
     * @param url      url
     * @param httpHead 请求头
     * @param bytes    字节流
     * @return 电子合同API返回值
     */
    public static ElectronicContractApiResult httpPutFile(String url, HttpHead httpHead, byte[] bytes) {
        String result = null;
        try {
            String token = CommonUtil.getToken();
            if (!StringUtils.isEmpty(token)) {
                result = HttpHelper.httpPutBytes(url, bytes, httpHead.getAllHeaders());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result, ElectronicContractApiResult.class);
    }

}
