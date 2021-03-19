package com.bc.app.server.service;


import com.bc.app.server.entity.GoodsOffer;
import com.github.pagehelper.PageInfo;

/**
 * @author qiu
 */
public interface GoodsOfferService {

    /**
     * 商品报价记录列表
     *
     * @param goodsId  企业id
     * @param pageNum  当前页
     * @param pageSize 每页显示个数
     * @return 商品报价记录列表
     */
    PageInfo<GoodsOffer> getGoodsOfferPageInfo(String goodsId, Integer pageNum, Integer pageSize);

}
