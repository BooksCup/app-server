package com.bc.app.server.service;

import com.bc.app.server.entity.AddressBook;

/**
 * 通讯录
 *
 * @author zhou
 */
public interface AddressBookService {

    /**
     * 新增通讯录
     *
     * @param addressBook 通讯录
     */
    void addAddressBook(AddressBook addressBook);

}
