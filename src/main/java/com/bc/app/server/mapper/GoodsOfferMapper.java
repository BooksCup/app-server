package com.bc.app.server.mapper;


import com.bc.app.server.entity.GoodsOffer;

import java.util.List;
import java.util.Map;

/**
 * 报价程序
 *
 * @author qiu
 */
public interface GoodsOfferMapper {


    List<GoodsOffer> getGoodsOfferPageInfo(Map<String, String> map);
}
