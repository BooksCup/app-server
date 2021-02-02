package com.bc.app.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.service.FabricQcWarehouseService;

import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mars
 * @description 获取宁外一个项目的供应商列表接口
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(VerifyCodeController.class);

    @Autowired
    FabricQcWarehouseService fabricQcWarehouseService;


    /**
     * 获取供应商列表
     *
     * @return
     * @throws IOException
     */
    @CrossOrigin
    @ApiOperation(value = "获取供应商列表", notes = "获取供应商列表")
    @GetMapping("list")
    public Map<String, Object> getSupplierList() throws IOException {
        Map<String, Object> map = new HashMap<String, Object>();
        String url = "http://42.192.134.170/cloud-enterprise/exchangeEnterprise/?isDelete=0&page=1&pageSize=400&currentEnterpriseId=1&name=&productName=&shortName=";
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        get.addHeader("enterpriseId", "1");
        CloseableHttpResponse response = httpClient.execute(get);
        HttpEntity responseEntity = response.getEntity();
        map.put("data", JSON.parse(EntityUtils.toString(responseEntity)));
        map.put("code", 0);
        System.out.println(map);
        return map;
    }


    /**
     * 获取物品列表
     * @return
     * @throws IOException
     */
    @CrossOrigin
    @ApiOperation(value = "获取物品列表", notes = "获取物品列表")
    @GetMapping("getMaterialLst")
    public Map<String, Object> materialList() throws IOException {
        Map<String, Object> map = new HashMap<>();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String returnValue = "这是默认返回值，接口调用失败";
        Map<String, Object> param = new HashMap<>();
        param.put("pageNum", 1);
        param.put("numPerPage", 3000);
        param.put("enterpriseId", 1);
        String url = "http://42.192.134.170/cloud-wms/goods";
        Object json = JSON.toJSON(param);

        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type", "application/json; charset=utf-8");
        post.setHeader("Connection", "Close");
        post.addHeader("enterpriseId", "1");
        StringEntity entity = new StringEntity(json.toString(), Charset.forName("UTF-8"));
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        CloseableHttpResponse response = client.execute(post);
        HttpEntity responseEntity = response.getEntity();


        map.put("data", JSON.parse(EntityUtils.toString(responseEntity)));
        map.put("code", 0);
        return map;
    }


}
