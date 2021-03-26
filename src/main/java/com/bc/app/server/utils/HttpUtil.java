package com.bc.app.server.utils;

import com.alibaba.fastjson.JSONObject;
import com.bc.app.server.entity.EContractApiResult;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具类
 *
 * @author zhou
 */
public class HttpUtil {

    /**
     * 测试地址
     */
    private static final String reqInterNme = "https://openapi.esign.cn";

    /**
     * appId 引用软件id
     */
    private static final String appId ="5111598374";


    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * get请求
     *
     * @param url url
     * @return 服务器返回
     */
    public static String doGet(String url) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                String result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * get请求
     *
     * @param url url
     * @return 服务器返回
     */
    public static String doGet(String url, Map<String, String> headerMap) {
        try {
            HttpClient client = HttpClientBuilder.create().build();
            // 发送get请求
            HttpGet request = new HttpGet(url);
            if (headerMap.size() > 0) {
                for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                    request.setHeader(entry.getKey(), entry.getValue());
                }
            }
            HttpResponse response = client.execute(request);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                String result = EntityUtils.toString(response.getEntity());
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求(用于key-value格式的参数)
     *
     * @param url    url
     * @param params 请求参数
     * @return 服务器返回
     */
    public static String doPost(String url, Map<String, String> params) {
        BufferedReader in;
        try {
            // 定义HttpClient  
            HttpClient client = HttpClientBuilder.create().build();
            // 实例化HTTP方法
            HttpPost request = new HttpPost();
            request.setURI(new URI(url));

            // 设置参数
            List<NameValuePair> nameValuePairs = new ArrayList<>();
            for (Iterator iterator = params.keySet().iterator(); iterator.hasNext(); ) {
                String name = (String) iterator.next();
                String value = String.valueOf(params.get(name));
                nameValuePairs.add(new BasicNameValuePair(name, value));
            }
            request.setEntity(new UrlEncodedFormEntity(nameValuePairs, StandardCharsets.UTF_8));

            HttpResponse response = client.execute(request);
            int code = response.getStatusLine().getStatusCode();
            if (code == HttpStatus.SC_OK) {
                // 请求成功
                in = new BufferedReader(new InputStreamReader(response.getEntity()
                        .getContent(), "utf-8"));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String separator = System.getProperty("line.separator");
                while ((line = in.readLine()) != null) {
                    sb.append(line + separator);
                }
                in.close();
                return sb.toString();
            } else {
                logger.info("statusCode: " + code);
                return null;
            }
        } catch (Exception e) {
            logger.error("[doPost] error, msg: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    /**
     * post请求（用于请求json格式的参数）
     *
     * @param url    url
     * @param params 参数
     * @return 服务器返回
     */
    public static String doPost(String url, String params) throws Exception {
        // 创建httpPost
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int statusCode = status.getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity);
                return jsonString;
            } else {
                logger.info("statusCode: " + statusCode + ", url: " + url);
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * post方法
     *
     * @param url 申请地址
     * @params JSONObject 传递的json
     * @params reqHeader 是否往header放置数据
     * @params splice 是否拼接默认的地址  默认true。
     */
    public static EContractApiResult httpPost(String url, JSONObject json, HttpHead reqHeader, Boolean splice){
        //放置header
        if (null == reqHeader) {
            reqHeader = new HttpHead();
        }
        String reqUri = url;
        if (splice) {
            reqUri = reqInterNme.concat(url);
        }
        String tokenJson = null;
        try {
            String token = CommonUtil.getToken();
            tokenJson = "";
            String jsonStr = json.toString();
            if (!StringUtils.isEmpty(token)) {
                reqHeader.setHeader("X-Tsign-Open-App-Id", appId);
                reqHeader.setHeader("X-Tsign-Open-Token", token);
                reqHeader.setHeader("Content-Type", "application/json");
                tokenJson = HttpHelper.httpPost(reqUri, reqHeader.getAllHeaders(), jsonStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (splice) {
            return JSONObject.parseObject(tokenJson, EContractApiResult.class);

        } else {
            return null;
        }
    }

    /**
     * put  主要用于put请求
     *
     * @param url 申请地址
     * @params bufferFile 上传文件的二进制字节流
     * @params reqHeader 是否往header放置数据
     * @params splice 是否拼接默认的地址  默认true。
     */
    public static EContractApiResult httpPut(String url, JSONObject json, HttpHead reqHeader, Boolean splice) throws Exception {
        //放置header
        if (null == reqHeader) {
            reqHeader = new HttpHead();
        }
        String reqUri = url;
        if (splice) {
            reqUri = reqInterNme.concat(url);
        }
        String token = CommonUtil.getToken();
        String tokenJson = "";
        String jsonStr = null;
        if (!org.springframework.util.StringUtils.isEmpty(json)){
            jsonStr = json.toString();
        }
        if (!StringUtils.isEmpty(token)) {
            reqHeader.addHeader("content-type", "application/json; charset=utf-8");
            reqHeader.setHeader("X-Tsign-Open-App-Id", appId);
            reqHeader.setHeader("X-Tsign-Open-Token", token);
            // tokenJson = HttpHelper.httpPut(reqUri, reqHeader.getAllHeaders(), filebytes);
            tokenJson = HttpHelper.httpPut(reqUri, reqHeader.getAllHeaders(),jsonStr);
        }
        if (splice) {
            return JSONObject.parseObject(tokenJson, EContractApiResult.class);
        } else {
            return null;
        }

    }

}