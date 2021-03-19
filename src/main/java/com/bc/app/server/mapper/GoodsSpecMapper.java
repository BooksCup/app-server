package com.bc.app.server.mapper;


/**
 * 商品价格程序
 *
 * @author qiu
 */
public interface GoodsSpecMapper {

    /**
     * 入库之后通过goodsid更新库存数量
     *
     * @param goodsId
     */
    void updateGoodsSpecResidualNumberByGoodsId(String goodsId);

}
