package com.bc.app.server.controller;


import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.Order;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.OrderService;
import com.bc.app.server.utils.CommonUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单
 *
 * @author qiu
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    /**
     * 获取订单分页信息
     *
     * @param enterpriseId 企业id
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 订单分页信息
     */
    @ApiOperation(value = "获取订单分页信息", notes = "获取订单分页信息")
    @GetMapping(value = "/search")
    public ResponseEntity<PageInfo<Order>> getOrderPageInfo(
            @RequestParam String enterpriseId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<Order>> responseEntity;
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("fromEnterpriseId", enterpriseId);
        map.put("keyword", keyword);
        try {
            PageInfo<Order> pageInfo = orderService.getOrderPageInfo(map, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 保存订单信息
     *
     * @param relatedCompanyId 往来单位id
     * @param relatedCompanyName 往来单位名称
     * @param goodsId  商品id
     * @param goodsName 品名
     * @param deliveryTime 交货日期
     * @param num 数量
     * @return
     */
    @ApiOperation(value = "保存订单信息", notes = "保存订单信息")
    @PostMapping(value = "")
    public ResponseEntity<String> addOrder(
            @RequestParam String relatedCompanyId,
            @RequestParam String relatedCompanyName,
            @RequestParam String goodsId,
            @RequestParam String goodsName,
            @RequestParam String themeTitle,
            @RequestParam String deliveryTime,
            @RequestParam String userId,
            @RequestParam String enterpriseId,
            @RequestParam String type,
            @RequestParam String remarks,
            @RequestParam String orderPhotos,
            @RequestParam String num) {
        ResponseEntity<String> responseEntity;
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        try {
            map.put("orderId",CommonUtil.generateId());
            map.put("relatedCompanyId",relatedCompanyId);
            map.put("relatedCompanyName",relatedCompanyName);
            map.put("goodsId",goodsId);
            map.put("goodsName",goodsName);
            map.put("deliveryTime",deliveryTime);
            map.put("applyCount",num);
            map.put("themeTitle",themeTitle);
            map.put("userId",userId);
            map.put("enterpriseId",enterpriseId);
            map.put("type", type);
            map.put("remarks", remarks);
            map.put("orderPhotos", orderPhotos);
            orderService.addOrder(map);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseMessage(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
