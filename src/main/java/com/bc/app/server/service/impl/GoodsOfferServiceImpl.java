package com.bc.app.server.service.impl;


import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.GoodsOffer;
import com.bc.app.server.mapper.GoodsOfferMapper;
import com.bc.app.server.service.GoodsOfferService;
import com.bc.app.server.utils.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("goodsOfferService")
public class GoodsOfferServiceImpl implements GoodsOfferService {

    @Autowired
    GoodsOfferMapper goodsOfferMapper;

    /**
     * 商品报价记录列表
     *
     * @param goodsId  企业id
     * @param pageNum  当前页
     * @param pageSize 每页显示个数
     * @return 商品报价记录列表
     */
    @Override
    public PageInfo<GoodsOffer> getGoodsOfferPageInfo(String goodsId, Integer pageNum, Integer pageSize) {
        Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("goodsId", goodsId);
        PageHelper.startPage(pageNum, pageSize);
        List<GoodsOffer> goodsOfferList = goodsOfferMapper.getGoodsOfferPageInfo(map);
        if (CollectionUtils.isNotEmpty(goodsOfferList)){
            for (GoodsOffer goodsOffer : goodsOfferList){
                goodsOffer.setOfferTime(CommonUtil.stringDateToResule(goodsOffer.getOfferTime()));
            }
        }
        return new PageInfo<>(goodsOfferList);
    }

}
