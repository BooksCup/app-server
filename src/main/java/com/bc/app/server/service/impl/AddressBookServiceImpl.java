package com.bc.app.server.service.impl;

import com.bc.app.server.entity.AddressBook;
import com.bc.app.server.mapper.AddressBookMapper;
import com.bc.app.server.service.AddressBookService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 通讯录
 *
 * @author zhou
 */
@Service("addressBookService")
public class AddressBookServiceImpl implements AddressBookService {

    @Resource
    AddressBookMapper addressBookMapper;

    /**
     * 新增通讯录
     *
     * @param addressBook 通讯录
     */
    @Override
    public void addAddressBook(AddressBook addressBook) {
        addressBookMapper.addAddressBook(addressBook);
    }

}
