package com.bc.app.server.service;

import com.bc.app.server.entity.Order;
import com.bc.app.server.entity.Theme;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * 订单应用程序
 *
 * @author qiu
 */
public interface OrderService {

    /**
     * 获取订单分页信息
     *
     * @param map      入参map
     * @param pageNum  当前页
     * @param pageSize 每页显示个数
     * @return 订单分页信息
     */
    PageInfo<Order> getOrderPageInfo(Map<String, String> map, Integer pageNum, Integer pageSize);

    /**
     * 保存订单
     *
     * @param order 订单信息
     * @param theme 主题信息
     *
     */
    void addOrder(Order order, Theme theme);
}
