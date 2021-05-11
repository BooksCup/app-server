package com.bc.app.server.controller;

import com.bc.app.server.entity.AddressBook;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.AddressBookService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 通讯录
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

}