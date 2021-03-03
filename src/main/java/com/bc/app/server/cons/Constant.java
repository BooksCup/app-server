package com.bc.app.server.cons;

/**
 * 常量类
 *
 * @author zhou
 */
public class Constant {

    // 验证码类型
    /**
     * 注册
     */
    public static final String VERIFY_CODE_TYPE_REGISTER = "0";

    /**
     * 短信服务URL
     */
    public static final String SERVICE_SMS_URL = "http://115.159.201.120:8086/third-party/sms";

    /**
     * 操作状态-未处理
     */
    public static final String OPERATE_STATUS_INIT = "0";

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

}
