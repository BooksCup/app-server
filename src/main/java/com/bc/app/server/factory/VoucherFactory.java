package com.bc.app.server.factory;

import com.bc.app.server.entity.FinanceVoucher;
import com.bc.app.server.entity.StockApplication;

/**
 * 凭证工厂类
 * @author qiu
 */
public interface VoucherFactory {

    public void autoAddSubjects(FinanceVoucher financeVoucher, StockApplication stockApplication);

}
