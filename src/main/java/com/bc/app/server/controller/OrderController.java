package com.bc.app.server.controller;


import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.DeliveryTime;
import com.bc.app.server.entity.Order;
import com.bc.app.server.entity.Theme;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.OrderService;
import com.bc.app.server.utils.CommonUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            @RequestParam(required = false) String goodsId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<Order>> responseEntity;
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("fromEnterpriseId", enterpriseId);
        map.put("keyword", keyword);
        map.put("goodsId", goodsId);
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
     * @param goodsId          商品id
     * @param userId           登录用户id
     * @param enterpriseId     企业id
     * @param relatedCompanyId 往来企业id
     * @param themeTitle       主题
     * @param deliveryTime     交货日期
     * @param type             类型
     * @param remarks          备注
     * @param orderPhotos      订单照片
     * @param num              交易数量
     * @return 返回值
     */
    @ApiOperation(value = "保存订单信息", notes = "保存订单信息")
    @PostMapping(value = "")
    public ResponseEntity<String> addOrder(
            @RequestParam String goodsId,
            @RequestParam String userId,
            @RequestParam String enterpriseId,
            @RequestParam String relatedCompanyId,
            @RequestParam(required = false) String themeTitle,
            @RequestParam(required = false) String deliveryTime,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String remarks,
            @RequestParam(required = false) String orderPhotos,
            @RequestParam(required = false) String num) {
        ResponseEntity<String> responseEntity;
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        try {
            Order order = new Order();
            String orderId = CommonUtil.generateId();
            order.setOrderId(orderId);
            order.setRelatedCompanyId(relatedCompanyId);
            order.setGoodsId(goodsId);
            map.put("deliveryTime", deliveryTime);
            order.setApplyCount(num);
            order.setFromUserId(userId);
            order.setFromEnterpriseId(enterpriseId);
            order.setType(type);
            order.setRemarks(remarks);
            order.setOrderPhotos(orderPhotos);
            List<DeliveryTime> deliveryTimeList = new ArrayList<>();
            DeliveryTime time = new DeliveryTime();
            order.setExtId(CommonUtil.generateId());
            time.setDeliveryTime(deliveryTime);
            time.setDeliveryCount(num);
            deliveryTimeList.add(time);
            order.setDeliveryDates(JSON.toJSONString(deliveryTimeList));

            Theme theme = new Theme();
            theme.setThemeTitle(themeTitle);
            theme.setUserId(userId);
            theme.setEnterpriseId(enterpriseId);
            orderService.addOrder(order, theme);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseMessage(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
