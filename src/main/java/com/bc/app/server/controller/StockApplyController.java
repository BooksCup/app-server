package com.bc.app.server.controller;

import com.alibaba.fastjson.JSONArray;
import com.bc.app.server.biz.JpushBiz;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.*;
import com.bc.app.server.enums.*;
import com.bc.app.server.service.*;
import com.bc.app.server.utils.BigDecimalUtil;
import com.bc.app.server.utils.CommonUtil;
import com.bc.app.server.utils.PlaceholderUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 出入库申请
 *
 * @author zhou
 */
@RestController
@RequestMapping("/stockApply")
public class StockApplyController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(StockApplyController.class);

    @Resource
    private StockApplicationService stockApplicationService;

    @Resource
    private PushTemplateService pushTemplateService;

    @Resource
    private UserService userService;

    @Resource
    private GoodsService goodsService;

    @Resource
    private SystemRoleConfigService systemRoleConfigService;

    @Resource
    private JpushBiz jpushBiz;

    @ApiOperation(value = "新增出入库申请", notes = "新增出入库申请")
    @PostMapping(value = "")
    public ResponseEntity<String> addStockApply(
            @RequestParam String goodsId,
            @RequestParam String createUserId,
            @RequestParam String enterpriseId,
            @RequestParam String stockType,
            @RequestParam String bizType,
            @RequestParam String specNums,
            @RequestParam(required = false) String orderId,
            @RequestParam(required = false) String relatedCompanyId,
            @RequestParam String applyStatus,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) String images,
            @RequestParam(required = false) String auditorId,
            @RequestParam(required = false) String copyId) {
        ResponseEntity<String> responseEntity;
        try {
            StockApplication s = new StockApplication();
            String stockApplicationId = CommonUtil.generateId();
            s.setId(stockApplicationId);
            s.setStockGoodsId(goodsId);
            s.setCreateUserId(createUserId);
            s.setEnterpriseId(enterpriseId);
            s.setContactEnterpriseId(relatedCompanyId);
            s.setRemark(remark);
            s.setStockImg(images);
            s.setAuditUserId(auditorId);
            s.setCopyUserId(copyId);
            s.setOrderId(orderId);
            s.setBizType(bizType);
            s.setStockType(stockType);
            s.setApplyStatus(applyStatus);
            s.setStockNumber("0");
            stockApplicationService.insert(s, specNums);

            processPush(enterpriseId, applyStatus, bizType, createUserId, goodsId, specNums, stockApplicationId);

            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 修改出入库申请
     *
     * @param goodsId          商品id
     * @param createUserId     创建者id
     * @param enterpriseId     企业id
     * @param stockType        出入库类型 1入库2出库
     * @param bizType          业务类型
     * @param specNums
     * @param orderId          订单id
     * @param relatedCompanyId 往来企业id
     * @param applyStatus      APP单据状态
     * @param remark           备注
     * @param images           照片
     * @param auditorId        审核人ID
     * @param copyId           抄送人id
     * @return
     */
    @ApiOperation(value = "修改出入库申请", notes = "修改出入库申请")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateStockApply(
            @PathVariable String id,
            @RequestParam String goodsId,
            @RequestParam String createUserId,
            @RequestParam String enterpriseId,
            @RequestParam String stockType,
            @RequestParam String bizType,
            @RequestParam String specNums,
            @RequestParam(required = false) String orderId,
            @RequestParam(required = false) String relatedCompanyId,
            @RequestParam String applyStatus,
            @RequestParam(required = false) String remark,
            @RequestParam(required = false) String images,
            @RequestParam(required = false) String auditorId,
            @RequestParam(required = false) String copyId) {
        ResponseEntity<String> responseEntity;
        try {
            StockApplication s = new StockApplication();
            s.setId(id);
            s.setStockGoodsId(goodsId);
            s.setCreateUserId(createUserId);
            s.setEnterpriseId(enterpriseId);
            s.setContactEnterpriseId(relatedCompanyId);
            s.setRemark(remark);
            s.setStockImg(images);
            s.setAuditUserId(auditorId);
            s.setCopyUserId(copyId);
            s.setOrderId(orderId);
            s.setBizType(bizType);
            s.setStockType(stockType);
            s.setApplyStatus(applyStatus);
            s.setStockNumber("0");
            stockApplicationService.update(s, specNums);

            processPush(enterpriseId, applyStatus, bizType, createUserId, goodsId, specNums, id);

            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    private void processPush(String enterpriseId, String applyStatus, String bizType, String createUserId, String goodsId, String specNums, String stockApplicationId) {
        // 提交后会通知仓管人员
        if (StockApplyStatusEnum.STOCK_APPLY_STATUS_UNCONFIRMED.getStatus().equals(applyStatus)) {
            PushTemplate pushTemplate = pushTemplateService.getPushTemplateMapperByServiceType(bizType);
            User user = userService.getUserById(createUserId).get(0);
            Goods goods = goodsService.getGoodsById(goodsId);
            Map<String, String> dataMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            dataMap.put("userName", user.getName());
            dataMap.put("stockBizType", StockBizTypeEnum.getValue(bizType));
            dataMap.put("goodsName", goods.getGoodsName());
            BigDecimal totalApplyNum = new BigDecimal(0);
            try {
                List<Map> inRecordList = JSONArray.parseArray(specNums, Map.class);
                for (Map inRecord : inRecordList) {
                    totalApplyNum = BigDecimalUtil.add(totalApplyNum, inRecord.get("applyNum"));
                }
            } catch (Exception e) {
                totalApplyNum = new BigDecimal(0);
            }

            dataMap.put("applyNum", String.valueOf(totalApplyNum.intValue()));
            dataMap.put("goodsUnit", goods.getGoodsUnit());
            Map<String, String> extrasMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            extrasMap.put("serviceType", bizType);
            extrasMap.put("stockInApplyId", stockApplicationId);


            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("enterpriseId", enterpriseId);
            paramMap.put("module", ModuleEnum.STOCK_IN.getCode());
            paramMap.put("role", RoleEnum.ADMIN.getCode());

            List<User> stockAdminUserList = systemRoleConfigService.getUserListByModuleAndRole(paramMap);
            for (User stockAdminUser : stockAdminUserList) {
                jpushBiz.sendPush(stockAdminUser.getId(), PlaceholderUtil.replace(pushTemplate.getTemplate(), dataMap), extrasMap);
            }

        }
    }

}
