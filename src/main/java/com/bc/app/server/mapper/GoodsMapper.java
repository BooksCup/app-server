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

    /**
     * 根据物品id查询是否使用过
     *
     * @param goodsId
     * @return
     */
    Integer getGoodsUsed(String goodsId);

    /**
     * 入库之后跟新物品总数量
     *
     * @param goodsId
     */
    void updateResidualNumberByGoodsId(String goodsId);
}
