package com.bc.app.server.mapper;

import com.bc.app.server.entity.Order;

import java.util.List;
import java.util.Map;

/**
 * 订单
 *
 * @author qiu
 */
public interface OrderMapper {

    /**
     * 获取订单列表
     *
     * @param map 参数map
     * @return 订单列表
     */
    List<Order> getOrderList(Map<String, String> map);

    /**
     * 保存订单
     *
     * @param order 订单
     */
    void saveOrder(Order order);

    /**
     * 保存订单详情
     *
     * @param order 订单
     */
    void saveOrderExt(Order order);

}
