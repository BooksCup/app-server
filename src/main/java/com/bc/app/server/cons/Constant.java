package com.bc.app.server.cons;

/**
 * 常量类
 *
 * @author zhou
 */
public class Constant {

    /**
     * 短信服务URL
     */
    public static final String SERVICE_SMS_URL = "http://115.159.201.120:8086/third-party/sms";

    /**
     * 操作状态-未处理
     */
    public static final String OPERATE_STATUS_INIT = "0";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 默认IM密码
     */
    public static final String DEFAULT_IM_PASSWORD = "123456";

    /**
     * 操作状态-同意
     */
    public static final String OPERATE_STATUS_AGREE = "1";

    /**
     * 初始化hashmap容量
     */
    public static final int DEFAULT_HASH_MAP_CAPACITY = 16;

    /**
     * 入库
     */
    public static final String STOCK_TYPE_IN = "1";

    /**
     * 出库
     */
    public static final String STOCK_TYPE_OUT = "2";

    /**
     * 人民币
     */
    public static final String CURRENCY_RMB = "9";

    /**
     * 人民币
     */
    public static final String INIT_PRICE = "9";

    /**
     * 已安装
     */
    public static final String IS_INSTALL = "1";

    /**
     * 未安装
     */
    public static final String IS_NOT_INSTALL = "0";


    /**
     * 库存现金
     */
    public static final String INVENTORY_AMOUNT = "1001";

    /**
     * 银行存款
     */
    public static final String BANK_DEPOSIT = "1002";


    /**
     * 应收账款
     */
    public static final String ACCOUNTS_RECEIVABLE = "1122";


    /**
     * 应交税费-应交增值税
     */
    public static final String SHOUNLD_PAY_FEES = "2221";

    /**
     * 银行手续费  财务费用-手续费
     */
    public static final String BANK_CHARGES = "560303";

    /**
     * 原材料
     */
    public static final String RAW_MATERIALS = "1403";


    /**
     * 应付账款
     */
    public static final String ACCOUNTS_PAYABLE = "2202";

    /**
     * 库存商品
     */
    public static final String STOCK_GOODS = "1405";


    /**
     * 委托加工物资
     */
    public static final String COMMISSIONED_MATERIALS = "1408";

    /**
     * 营业外收入
     */
    public static final String NON_OPERATING_INCOME = "5301";


    /**
     * 营业外支出
     */
    public static final String NON_OPERATING_EXPENSES = "5711";

    /**
     * 管理费用
     */
    public static final String MANAGE_FEE = "5602";

    /**
     * 销售费用
     */
    public static final String SALE_FE = "5601";

    //借贷
    /**
     * 借
     */
    public static final String BORROW = "1";

    /**
     * 贷
     */
    public static final String LOAN = "2";

    public static final String E_CONTRACT_BASE_URL = "https://openapi.esign.cn";

    public static final String REAL_TYPE_YES = "1";

    public static final String FONT_PATH_LINUX = "/usr/share/fonts/chinese/SimSun.ttf";
    public static final String FONT_PATH_WINDOWS = "D://simsun.ttc";

    /**
     * 协议前缀 - "https"
     */
    public static final String PROTOCOL_HTTPS_PREFIX = "https:";

    /**
     * 协议前缀 - "https"
     */
    public static final String PROTOCOL_HTTP_PREFIX = "http:";

}
