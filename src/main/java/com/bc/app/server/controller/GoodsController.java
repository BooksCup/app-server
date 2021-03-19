package com.bc.app.server.controller;

import com.bc.app.server.entity.Goods;
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
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    GoodsService goodsService;

    /**
     * 获取物品列表
     *
     * @param enterpriseId 企业id
     * @param keyword      搜索关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 商品列表
     */
    @ApiOperation(value = "获取物品列表", notes = "获取物品列表")
    @GetMapping(value = "/search")
    public ResponseEntity<PageInfo<Goods>> getGoodsPageInfo(
            @RequestParam String enterpriseId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<Goods>> responseEntity;
        try {
            PageInfo<Goods> pageInfo = goodsService.getGoodsList(enterpriseId, keyword, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 根据物品ID获取物品详情
     *
     * @param goodsId 物品ID
     * @return 物品详情
     */
    @CrossOrigin
    @ApiOperation(value = "根据物品ID获取物品详情", notes = "根据物品ID获取物品详情")
    @GetMapping(value = "/{goodsId}")
    public ResponseEntity<Goods> getGoodsById(
            @PathVariable String goodsId) {
        ResponseEntity<Goods> responseEntity;
        try {
            Goods goods = goodsService.getGoodsById(goodsId);
            responseEntity = new ResponseEntity<>(goods, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new Goods(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
