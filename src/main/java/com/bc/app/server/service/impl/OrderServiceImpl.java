package com.bc.app.server.service.impl;

import com.bc.app.server.entity.Goods;
import com.bc.app.server.entity.Order;
import com.bc.app.server.mapper.GoodsMapper;
import com.bc.app.server.mapper.OrderMapper;
//import com.bc.app.server.mapper.ThemeMapper;
import com.bc.app.server.service.OrderService;
import com.bc.app.server.utils.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 订单应用程序
 *
 * @author qiu
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;
//    @Autowired
//    ThemeMapper themeMapper;

    /**
     * 获取订单分页信息
     *
     * @param map      入参map
     * @param pageNum  当前页
     * @param pageSize 每页显示个数
     * @return 订单分页信息
     */
    @Override
    public PageInfo<Order> getOrderPageInfo(Map<String, String> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList = orderMapper.getOrderList(map);
        return new PageInfo<>(orderList);
    }

    /**
     * 保存订单
     *
     * @param map 入参
     */
    @Override
    public void addOrder(Map<String, String> map) {
        Goods goods = goodsMapper.selectById(map.get("goodsId"));
        map.put("goodsPhotos",goods.getGoodsPhotos());
        map.put("goodsName",goods.getGoodsName());
        map.put("goodsNo",goods.getGoodsNo());
        map.put("goodsTypeId",goods.getGoodsTypeId());
        map.put("goodsTypeName",goods.getGoodsTypeName());
        map.put("no", CommonUtil.getOrderNo());
        map.put("themeId", CommonUtil.generateId());
        //保存订单相关信息
        orderMapper.saveOrder(map);
        //保存主题
//        themeMapper.saveTheme(map);
    }

}
