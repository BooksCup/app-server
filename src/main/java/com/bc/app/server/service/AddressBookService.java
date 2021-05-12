package com.bc.app.server.service;

import com.bc.app.server.entity.AddressBook;
import com.github.pagehelper.PageInfo;

import java.util.Map;

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

    /**
     * 查询通讯录分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 通讯录分页信息
     */
    PageInfo<AddressBook> getAddressBookPageInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize);

    /**
     * 根据ID查找通讯录
     *
     * @param id ID
     * @return 通讯录
     */
    AddressBook getAddressBookById(String id);

    /**
     * 删除通讯录
     *
     * @param id 通讯录ID
     */
    void deleteAddressBook(String id);

}