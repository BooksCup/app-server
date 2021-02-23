package com.bc.app.server.service;


import com.bc.app.server.entity.Goods;
import com.github.pagehelper.PageInfo;

/**
 * 商品应用程序
 *
 * @author qiu
 */
public interface GoodsService {

    /**
     * 获取商品列表
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 商品列表
     */
    PageInfo<Goods> getGoodsList(String enterpriseId, String keyword, Integer pageNum, Integer pageSize);

}
