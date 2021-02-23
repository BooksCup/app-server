package com.bc.app.server.mapper;

import com.bc.app.server.entity.Order;
import com.github.pagehelper.Page;

import java.util.List;
import java.util.Map;

/**
 * 应用程序
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

    void saveOrder(Map<String, String> map);
}
