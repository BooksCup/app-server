package com.bc.app.server.controller;

import com.bc.app.server.biz.JpushBiz;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.enums.PushServiceTypeEnum;
import com.bc.app.server.enums.ResponseMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 推送
 *
 * @author zhou
 */
@RestController
@RequestMapping("/push")
public class PushController {

    @Resource
    private JpushBiz jpushBiz;

    @ApiOperation(value = "测试推送", notes = "测试推送")
    @PostMapping(value = "")
    public ResponseEntity<String> sendPush(@RequestParam String alias,
                                           @RequestParam String alert) {
        ResponseEntity<String> responseEntity;
        try {
            Map<String, String> extras = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            extras.put("serviceType", PushServiceTypeEnum.ADD_STOCK_IN_APPLY_OFFICE_SUPPLIES.getCode());
            extras.put("stockInApplyId", "049770e28631495cb1aecc04e8fbcf25");
            jpushBiz.sendPush(alias, alert, extras);
            responseEntity = new ResponseEntity<>(ResponseMsg.PUSH_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(ResponseMsg.PUSH_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
