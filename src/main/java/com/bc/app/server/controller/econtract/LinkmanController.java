package com.bc.app.server.controller.econtract;

import com.bc.app.server.entity.econtract.Linkman;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.LinkmanService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 合同联系人
 *
 * @author zhou
 */
@RestController
@RequestMapping("/linkman")
public class LinkmanController {

    @Resource
    LinkmanService linkmanService;

    @ApiOperation(value = "新增合同联系人", notes = "新增合同联系人")
    @PostMapping(value = "")
    public ResponseEntity<String> addLinkman(
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String enterpriseName,
            @RequestParam(required = false) String remark) {
        ResponseEntity<String> responseEntity;
        try {
            Linkman linkman = new Linkman(name, phone, email, enterpriseName, remark);
            linkmanService.addLinkman(linkman);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
