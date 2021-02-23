package com.bc.app.server.service.impl;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.Goods;
import com.bc.app.server.mapper.GoodsMapper;
import com.bc.app.server.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 商品应用程序
 *
 * @author qiu
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    /**
     * 获取商品列表
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 商品列表
     */
    @Override
    public PageInfo<Goods> getGoodsList(String enterpriseId, String keyword, Integer pageNum, Integer pageSize) {
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("enterpriseId", enterpriseId);
        map.put("keyword", keyword);
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> page = goodsMapper.getGoodsList(map);
        return new PageInfo<>(page);
    }

}
