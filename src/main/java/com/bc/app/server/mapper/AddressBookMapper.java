package com.bc.app.server.mapper;

import com.bc.app.server.entity.AddressBook;

import java.util.List;
import java.util.Map;

/**
 * 通讯录
 *
 * @author zhou
 */
public interface AddressBookMapper {

    /**
     * 新增通讯录
     *
     * @param addressBook 通讯录
     */
    void addAddressBook(AddressBook addressBook);

    /**
     * 获取通讯录列表
     *
     * @param paramMap 参数map
     * @return 通讯录列表
     */
    List<AddressBook> getAddressBookList(Map<String, Object> paramMap);

    /**
     * 根据通讯录ID查找通讯录
     *
     * @param id 通讯录ID
     * @return 通讯录
     */
    AddressBook getAddressBookById(String id);

    /**
     * 根据通讯录ID列表查找通讯录列表
     *
     * @param idList 通讯录ID列表
     * @return 通讯录列表
     */
    List<AddressBook> getAddressBookListByIdList(List<String> idList);

    /**
     * 删除通讯录
     *
     * @param id 通讯录ID
     */
    void deleteAddressBook(String id);

}