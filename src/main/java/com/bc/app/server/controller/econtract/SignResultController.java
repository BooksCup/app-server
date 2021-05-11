package com.bc.app.server.controller.econtract;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.econtract.SignResult;
import com.bc.app.server.service.ContractService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 签署结果(回调)
 *
 * @author zhou
 */
@RestController
@RequestMapping("/signResult")
public class SignResultController {

    @Resource
    private ContractService contractService;

    @ApiOperation(value = "签署人签署完成回调通知", notes = "签署人签署完成回调通知")
    @PostMapping(value = "")
    public ResponseEntity<String> getSignResult(
            @RequestBody SignResult signResult) {
        Map<String, Object> resultMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        resultMap.put("code", 200);
        if ("SIGN_FLOW_UPDATE".equals(signResult.getAction())) {
            Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("id", signResult.getThirdOrderNo());
            paramMap.put("sendStatus", signResult.getSignResult());
            contractService.updateContractSendStatus(paramMap);
        }
        return new ResponseEntity<>(JSON.toJSONString(resultMap), HttpStatus.OK);
    }

}
