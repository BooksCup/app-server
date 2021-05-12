package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.AddressBook;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.AddressBookService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户通讯录
 *
 * @author zhou
 */
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(AddressBookController.class);

    @Resource
    AddressBookService addressBookService;

    @ApiOperation(value = "新增通讯录", notes = "新增通讯录")
    @PostMapping(value = "")
    public ResponseEntity<String> addAddressBook(
            @RequestParam String userId,
            @RequestParam String name,
            @RequestParam String phones,
            @RequestParam String images,
            @RequestParam(required = false) String tels,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String company,
            @RequestParam(required = false) String job,
            @RequestParam(required = false) String website,
            @RequestParam(required = false) String address,
            @RequestParam(required = false) String tags,
            @RequestParam(required = false) String remark) {
        ResponseEntity<String> responseEntity;
        try {
            AddressBook addressBook = new AddressBook(userId, name, images, phones, tels, email,
                    company, job, website, address, tags, remark);
            addressBookService.addAddressBook(addressBook);
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("[addAddressBook] error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.
                    ADD_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取通讯录分页信息
     *
     * @param userId   用户ID
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 通讯录分页信息
     */
    @ApiOperation(value = "获取通讯录分页信息", notes = "获取通讯录分页信息")
    @GetMapping(value = "/search")
    public ResponseEntity<PageInfo<AddressBook>> getAddressBookPageInfo(
            @RequestParam String userId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ResponseEntity<PageInfo<AddressBook>> responseEntity;
        Map<String, Object> map = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        map.put("userId", userId);
        try {
            PageInfo<AddressBook> pageInfo = addressBookService.getAddressBookPageInfo(map, pageNum, pageSize);
            responseEntity = new ResponseEntity<>(pageInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(new PageInfo<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}