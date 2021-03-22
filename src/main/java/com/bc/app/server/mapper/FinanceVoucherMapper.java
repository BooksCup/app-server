package com.bc.app.server.mapper;


import com.bc.app.server.entity.FinanceVoucher;

import java.util.Map;

/**
 * @author qiu
 */
public interface FinanceVoucherMapper {

    /**
     * 找到当前日期下的数据量*
     *
     * @param map 入参
     */
    int findTotalNumberByDate(Map<String, String> map);

    /**
     * 保存凭证
     *
     * @param financeVoucher 入参
     */
    void insert(FinanceVoucher financeVoucher);

}
