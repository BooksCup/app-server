package com.bc.app.server.controller.econtract;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.econtract.Linkman;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.LinkmanService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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

    /**
     * 新增合同联系人
     *
     * @param userId       用户ID
     * @param enterpriseId 企业ID
     * @param name         姓名
     * @param phone        手机号
     * @param email        邮箱
     * @param companyName  所属企业名
     * @param remark       备注
     * @return ResponseEntity
     */
    @ApiOperation(value = "新增合同联系人", notes = "新增合同联系人")
    @PostMapping(value = "")
    public ResponseEntity<String> addLinkman(
            @RequestParam String userId,
            @RequestParam String enterpriseId,
            @RequestParam String name,
            @RequestParam String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String remark) {
        ResponseEntity<String> responseEntity;
        try {
            Linkman linkman = new Linkman(userId, enterpriseId, name, phone, email, companyName, remark);
            linkmanService.addLinkman(linkman);
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取合同联系人分页信息
     *
     * @param enterpriseId 企业ID
     * @param pageNum      当前分页数
     * @param pageSize     分页大小
     * @return 合同联系人分页信息
     */
    @ApiOperation(value = "获取合同联系人分页信息", notes = "获取合同联系人分页信息")
    @GetMapping(value = "")
    public ResponseEntity<PageInfo<Linkman>> getLinkmanPageInfo(
            @RequestParam String enterpriseId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<Linkman>> responseEntity;
        Map<String, Object> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        paramMap.put("enterpriseId", enterpriseId);
        try {
            PageInfo<Linkman> linkmanPageInfo = linkmanService.getLinkmanPageInfo(paramMap, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(linkmanPageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 修改合同联系人
     *
     * @param id          合同联系人ID
     * @param name        姓名
     * @param phone       手机号
     * @param email       邮箱
     * @param companyName 所属企业名
     * @param remark      备注
     * @return ResponseEntity
     */
    @ApiOperation(value = "修改合同联系人", notes = "修改合同联系人")
    @PutMapping(value = "/{id}")
    public ResponseEntity<String> updateLinkman(
            @PathVariable String id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String remark) {
        ResponseEntity<String> responseEntity;
        try {
            Linkman linkman = new Linkman(name, phone, email, companyName, remark);
            linkman.setId(id);
            linkmanService.updateLinkman(linkman);
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.UPDATE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 删除合同联系人
     *
     * @param id 合同联系人ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "删除合同联系人", notes = "删除合同联系人")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteLinkman(
            @PathVariable String id) {
        ResponseEntity<String> responseEntity;
        try {
            linkmanService.deleteLinkman(id);
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}