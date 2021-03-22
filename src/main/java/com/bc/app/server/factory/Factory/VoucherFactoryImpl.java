package com.bc.app.server.factory.Factory;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.FianceSubjectVoucher;
import com.bc.app.server.entity.FinanceVoucher;
import com.bc.app.server.entity.StockApplication;
import com.bc.app.server.factory.VoucherFactory;
import com.bc.app.server.mapper.FinanceSubjectVoucherMapper;
import com.bc.app.server.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 凭证工厂类的实现
 */
@Component
public class VoucherFactoryImpl implements VoucherFactory {


    @Autowired
    FinanceSubjectVoucherMapper financeSubjectVoucherMapper;



    /**
     * 根据类型自动增加科目数据
     *
     * @param financeVoucher
     * @return
     */
    @Override
    public void autoAddSubjects(FinanceVoucher financeVoucher, StockApplication stockApplication) {
        String voucherId = financeVoucher.getId();
        String enterpriseId = financeVoucher.getEnterpriseId();
        String voucherType = financeVoucher.getVoucherType();

        FianceSubjectVoucher fianceSubjectVoucher = new FianceSubjectVoucher();
        List<FianceSubjectVoucher> fianceSubjectVoucherList = new ArrayList<>(3);
        //应付账款
        BigDecimal shouldPayAmount = new BigDecimal("0.000");

        //应付账款  （含税）
        if (!StringUtils.isEmpty(stockApplication.getTotalAmount())) {
            shouldPayAmount = new BigDecimal(stockApplication.getTotalAmount());
            BigDecimal bd = shouldPayAmount.setScale(3, RoundingMode.UP);
            fianceSubjectVoucher.setAmount(bd + "");
        }

        if (!StringUtils.isEmpty(voucherId) && !StringUtils.isEmpty(enterpriseId)) {
            //材料付款会新增三条科目
            for (int i = 0; i < 5; i++) {
                fianceSubjectVoucher = new FianceSubjectVoucher();
                fianceSubjectVoucher.setId(CommonUtil.generateId());
                fianceSubjectVoucher.setVoucherId(voucherId);
                fianceSubjectVoucher.setCurrency(financeVoucher.getCurrency());
                fianceSubjectVoucher.setRemark(financeVoucher.getRemark());
                fianceSubjectVoucher.setJoinsStatus(true);
                fianceSubjectVoucher.setSort(i + "");
                //根据不同的类型进行分别放置数据
                fianceSubjectVoucher = distinguishTypes(voucherType, fianceSubjectVoucher, stockApplication, i);
               // if (!StringUtils.isEmpty(voucherQuery.getRefundType())) { //数据类型，传值为退回
                fianceSubjectVoucher.setDirection("-" + fianceSubjectVoucher.getAmount());

                if (fianceSubjectVoucher.getJoinsStatus()) {
                    fianceSubjectVoucherList.add(fianceSubjectVoucher);
                }
            }
            if (!CollectionUtils.isEmpty(fianceSubjectVoucherList)) {
                financeSubjectVoucherMapper.addSubjectVoucherList(fianceSubjectVoucherList);
            }

        }

    }



    /***
     *  根据不同的项目类型进行分别放置数据
     * dd
     * @param voucherType 业务类型
     * @param fianceSubjectVoucher 放入数据的关联表
     * @param stockApplication
     * @param i 循环次数，用来判断存储几条数据
     */
//    public FianceSubjectVoucher distinguishTypes(String voucherType, FianceSubjectVoucher fianceSubjectVoucher,
//                                                 VoucherQuery voucherQuery, int i, String total, String bankCost, String feeTotal, String otherTotal
//            , String incomeTotal)
    public FianceSubjectVoucher  distinguishTypes (String voucherType, FianceSubjectVoucher fianceSubjectVoucher,
            StockApplication stockApplication, int i){

        // 金额统一两位小数
        if (!StringUtils.isEmpty(fianceSubjectVoucher.getAmount())) {
            fianceSubjectVoucher.setAmount(new BigDecimal(fianceSubjectVoucher.getAmount()).setScale(2, RoundingMode.HALF_UP).toString());
        }
        if (voucherType.equals("41")) {
            //办公用品入库  标识号 41   voucherQuery.getCollectionUnit()收款单位
            if (i == 0) {
                //借：库存商品-办公用品
                dealFianceSubjectVoucher(fianceSubjectVoucher, Constant.BORROW, "库存商品-办公用品",
                        Constant.STOCK_GOODS, "", fianceSubjectVoucher.getAmount());
            } else if (i == 1) {
                // 借: 应交税费-应交增值税（进项税额）	税率默认为0
                if (StringUtils.isEmpty(stockApplication.getTax()) || "0".equals(stockApplication.getTax())) {
                    //贷：应付账款-往来单位
                    dealFianceSubjectVoucher(fianceSubjectVoucher, Constant.LOAN, "应付账款-" + "",
                            Constant.ACCOUNTS_RECEIVABLE, "", fianceSubjectVoucher.getAmount());
                    fianceSubjectVoucher.setJoinsStatus(false);
                } else {
                    String taxFee = new BigDecimal(fianceSubjectVoucher.getAmount()).multiply(new BigDecimal(StringUtils.isEmpty(stockApplication.getTax())?"0":stockApplication.getTax())).toString();
                    taxFee = new BigDecimal(taxFee).setScale(2, RoundingMode.HALF_UP).toString();
                    dealFianceSubjectVoucher(fianceSubjectVoucher, Constant.BORROW, "应交税费-应交增值税（进项税额）",
                            Constant.SHOUNLD_PAY_FEES, "", taxFee);
                }
            } else if (i == 2) {
                //贷：应付账款-往来单位
                dealFianceSubjectVoucher(fianceSubjectVoucher, Constant.LOAN, "应付账款-" + "",
                        Constant.ACCOUNTS_RECEIVABLE,"", fianceSubjectVoucher.getAmount());
            } else {
                fianceSubjectVoucher.setJoinsStatus(false);
            }
        }else if (voucherType.equals("42")) {
            //办公用品出库  标识号 42
            if (i == 0) {
                //借：销售费用-办公费
                dealFianceSubjectVoucher(fianceSubjectVoucher, Constant.BORROW, "管理费用-办公费",
                        Constant.MANAGE_FEE, "", fianceSubjectVoucher.getAmount());
            } else if (i == 1) {
                //贷：库存商品-办公用品
                dealFianceSubjectVoucher(fianceSubjectVoucher,Constant.LOAN, "库存商品-办公用品",
                        Constant.STOCK_GOODS, "", fianceSubjectVoucher.getAmount());
            } else {
                fianceSubjectVoucher.setJoinsStatus(false);
            }
        } else {
            //其他类型不生成凭证
            fianceSubjectVoucher.setJoinsStatus(false);
        }
        return fianceSubjectVoucher;

    }

    private void dealFianceSubjectVoucher(FianceSubjectVoucher fianceSubjectVoucher, String type, String target, String subjectCode, String main, String amount) {
        fianceSubjectVoucher.setDirection(type);
        fianceSubjectVoucher.setTarget(target);
        fianceSubjectVoucher.setSubjectCode(subjectCode);
        fianceSubjectVoucher.setMain(main);
        fianceSubjectVoucher.setAmount(amount);
    }
}
