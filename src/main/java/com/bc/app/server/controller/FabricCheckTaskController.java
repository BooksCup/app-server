package com.bc.app.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.FabricCheckTask;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.FabricCheckTaskService;
import com.bc.app.server.utils.CommonUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 面料盘点任务
 *
 * @author zhou
 */
@RestController
@RequestMapping("/fabricCheckTask")
public class FabricCheckTaskController {

    @Autowired
    FabricCheckTaskService fabricCheckTaskService;

    /**
     * 获取面料盘点任务分页信息
     *
     * @param enterpriseId 企业id
     * @param keyword      关键字
     * @param pageNum      当前页
     * @param pageSize     每页显示个数
     * @return 面料盘点任务分页信息
     */
    @ApiOperation(value = "获取面料盘点任务分页信息", notes = "获取面料盘点任务分页信息")
    @GetMapping(value = "/search")
    public ResponseEntity<PageInfo<FabricCheckTask>> getFabricCheckTaskPageInfo(
            @RequestParam String enterpriseId,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String modifyTimeApply,
            @RequestParam(required = false) String modifyTimeExamine,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<FabricCheckTask>> responseEntity;
        try {
            PageInfo<FabricCheckTask> pageInfo = fabricCheckTaskService.getFabricCheckTaskPageInfo(enterpriseId, keyword, pageNum, pageSize,
                    modifyTimeApply, modifyTimeExamine);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;

    }

    /**
     * 添加面料盘点任务信息
     *
     * @param goodsName               品名
     * @param goodsNo                 商品编号
     * @param goodsPhotos             商品照片
     * @param goodsId                 商品id
     * @param relatedCompanyName      企业名称
     * @param relatedCompanyShortName 企业简称
     * @param orderNo                 订单编号
     * @param orderId                 订单id
     * @param orderTheme              订单主题
     * @return
     */
    @ApiOperation(value = "添加面料盘点任务信息", notes = "添加面料盘点任务信息")
    @PostMapping(value = "")
    public ResponseEntity<String> addFabricCheckTask(
            @RequestParam String goodsId,
            @RequestParam String orderId,
            @RequestParam String enterpriseId,
            @RequestParam String relatedCompanyId,
            @RequestParam(required = false) String goodsName,
            @RequestParam(required = false) String goodsNo,
            @RequestParam(required = false) String goodsPhotos,
            @RequestParam(required = false) String relatedCompanyName,
            @RequestParam(required = false) String relatedCompanyShortName,
            @RequestParam(required = false) String deliveryDate,
            @RequestParam(required = false) String orderNo,
            @RequestParam(required = false) String orderTheme) {
        FabricCheckTask fabricCheckTask = new FabricCheckTask(goodsName, goodsNo, goodsPhotos, goodsId, relatedCompanyName,
                relatedCompanyShortName, orderNo, orderTheme, orderId, relatedCompanyId, enterpriseId, deliveryDate);
        ResponseEntity<String> responseEntity;
        try {
            fabricCheckTaskService.addFabricCheckTask(fabricCheckTask);
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 通过id更新数据任务数据
     *
     * @param fabricCheckTaskStr json字符串
     * @return
     */
    @ApiOperation(value = "通过id更新数据", notes = "通过id更新数据")
    @PutMapping(value = "")
    public ResponseEntity<String> batchUpdateFabricCheckTaskById(
            @RequestParam String fabricCheckTaskStr) {
        try {
            FabricCheckTask fabricCheckTask = JSON.parseObject(fabricCheckTaskStr, FabricCheckTask.class);
            fabricCheckTaskService.batchUpdateFabricCheckTaskById(fabricCheckTask);
            return new ResponseEntity<>(ResponseMsg.
                    UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(ResponseMsg.
                    UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * 删除数据
     *
     * @param id id
     * @return
     */
    @ApiOperation(value = "删除数据", notes = "删除数据")
    @PutMapping(value = "/updateById")
    public ResponseEntity<String> updateById(@RequestParam(value = "id") String id) {
        try {
            Map<String, String> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            map.put("is_delete", "0");
            map.put("id", id);
            fabricCheckTaskService.updateById(map);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(ResponseMsg.
                    UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ResponseMsg.
                UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
    }


}
