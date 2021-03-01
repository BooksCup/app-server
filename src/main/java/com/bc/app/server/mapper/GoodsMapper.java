package com.bc.app.server.mapper;

import com.bc.app.server.entity.Goods;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品程序
 *
 * @author qiu
 */
public interface GoodsMapper {

    /**
     * 获取商品列表
     *
     * @param map 入参
     * @return 商品列表
     */
    List<Goods> getGoodsList(Map<String, String> map);

    /**
     * 通过id获取商品
     *
     * @param id 商品id
     * @return 商品信息
     */
    Goods selectById(@Param("id") String id);
}