package com.bc.app.server.controller;

import com.bc.app.server.entity.Goods;
import com.bc.app.server.entity.GoodsOffer;
import com.bc.app.server.service.GoodsOfferService;
import com.bc.app.server.service.GoodsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 物品
 *
 * @author zhou
 */
@RestController
@RequestMapping("/goodsOffer")
public class GoodsOfferController {

    @Autowired
    GoodsOfferService goodsOfferService;

    /**
     * 商品报价记录列表
     *
     * @param goodsId 物品ID
     * @return 商品报价记录列表
     */
    @CrossOrigin
    @ApiOperation(value = "商品报价记录列表", notes = "商品报价记录列表")
    @GetMapping(value = "/{goodsId}")
    public ResponseEntity<PageInfo<GoodsOffer>> getGoodsOfferPageInfo(
            @PathVariable String goodsId,
            @RequestParam(defaultValue = "1", required = false) Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<GoodsOffer>> responseEntity;
        try {
            PageInfo<GoodsOffer> pageInfo = goodsOfferService.getGoodsOfferPageInfo(goodsId, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
