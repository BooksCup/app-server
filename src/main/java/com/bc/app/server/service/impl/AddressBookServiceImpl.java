package com.bc.app.server.service.impl;

import com.bc.app.server.entity.AddressBook;
import com.bc.app.server.mapper.AddressBookMapper;
import com.bc.app.server.service.AddressBookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    /**
     * 查询通讯录分页信息
     *
     * @param paramMap 参数map
     * @param pageNum  当前分页数
     * @param pageSize 分页大小
     * @return 通讯录分页信息
     */
    @Override
    public PageInfo<AddressBook> getAddressBookPageInfo(Map<String, Object> paramMap, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<AddressBook> addressBookList = addressBookMapper.getAddressBookList(paramMap);
        return new PageInfo<>(addressBookList);
    }

    /**
     * 根据ID查找通讯录
     *
     * @param id ID
     * @return 通讯录
     */
    @Override
    public AddressBook getAddressBookById(String id) {
        return addressBookMapper.getAddressBookById(id);
    }

    /**
     * 根据通讯录ID列表查找通讯录列表
     *
     * @param idList 通讯录ID列表
     * @return 通讯录列表
     */
    @Override
    public List<AddressBook> getAddressBookListByIdList(List<String> idList) {
        return addressBookMapper.getAddressBookListByIdList(idList);
    }

    /**
     * 删除通讯录
     *
     * @param id 通讯录ID
     */
    @Override
    public void deleteAddressBook(String id) {
        addressBookMapper.deleteAddressBook(id);
    }

}