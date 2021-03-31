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

    public static ElectronicContractApiResult httpPost(String url, JSONObject json) {
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
        return JSONObject.parseObject(result, ElectronicContractApiResult.class);
    }

    public static ElectronicContractApiResult httpPost(String url, String json) {
        String result = null;
        try {
            String token = CommonUtil.getToken();
            if (!StringUtils.isEmpty(token)) {
                HttpHead httpHead = new HttpHead();
                httpHead.setHeader("X-Tsign-Open-App-Id", appId);
                httpHead.setHeader("X-Tsign-Open-Token", token);
                httpHead.setHeader("Content-Type", "application/json");
                result = HttpHelper.httpPost(url, httpHead.getAllHeaders(), json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result, ElectronicContractApiResult.class);
    }


    public static ElectronicContractApiResult httpPut(String url, JSONObject json) {
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
        return JSONObject.parseObject(result, ElectronicContractApiResult.class);
    }

    /**
     * post方法  主要用于文件传输
     *
     * @param url 申请地址
     * @params bufferFile 上传文件的二进制字节流
     * @params reqHeader 是否往header放置数据
     * @params splice 是否拼接默认的地址  默认true。
     */
    public static ElectronicContractApiResult httpPutFile(String url, HttpHead reqHeader, byte[] filebytes) {
        String result = null;
        try {
            String token = CommonUtil.getToken();
            if (!StringUtils.isEmpty(token)) {
                HttpHead httpHead = new HttpHead();
                httpHead.addHeader("content-type", "application/json; charset=utf-8");
                httpHead.setHeader("X-Tsign-Open-App-Id", appId);
                httpHead.setHeader("X-Tsign-Open-Token", token);
                result = HttpHelper.httpPutByte2(url, filebytes, reqHeader.getAllHeaders());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(result, ElectronicContractApiResult.class);

    }

}
