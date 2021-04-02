package com.bc.app.server.utils;

import java.math.BigDecimal;

/**
 * 精确运算工具类
 *
 * @author zhou
 */
public class BigDecimalUtil {

    /**
     * 除法运算默认精度
     */
    private static final int DEF_DIV_SCALE = 10;

    private BigDecimalUtil() {

    }

    public static BigDecimal getIntegerValue(Object value){
        BigDecimal b;
        try {
            b = new BigDecimal(String.valueOf(value));
        } catch (Exception e) {
            b = new BigDecimal("0.00");
        }
        return b.setScale(0, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 精确加法
     *
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static BigDecimal add(Object value1, Object value2) {
        BigDecimal b1;
        BigDecimal b2;
        try {
            b1 = new BigDecimal(String.valueOf(value1));
        } catch (Exception e) {
            b1 = new BigDecimal("0.00");
        }
        try {
            b2 = new BigDecimal(String.valueOf(value2));
        } catch (Exception e) {
            b2 = new BigDecimal("0.00");
        }
        return b1.add(b2);
    }

    /**
     * 　　* 精确减法
     */
    public static double sub(double value1, double value2) {
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 　　* 精确乘法
     */

    public static BigDecimal multiply(Object value1, Object value2) {
        BigDecimal b1;
        BigDecimal b2;
        try {
            b1 = new BigDecimal(String.valueOf(value1));
        } catch (Exception e) {
            b1 = new BigDecimal("0.00");
        }
        try {
            b2 = new BigDecimal(String.valueOf(value2));
        } catch (Exception e) {
            b2 = new BigDecimal("0.00");
        }
        return b1.multiply(b2);
    }

    /**
     * 　　* 精确除法 使用默认精度
     */

    public static double div(double value1, double value2) throws IllegalAccessException {
        return div(value1, value2, DEF_DIV_SCALE);
    }


    /**
     * 　　* 精确除法
     * 　　*
     * 　　* @param scale
     * 　　* 精度
     */
    public static double div(double value1, double value2, int scale) throws IllegalAccessException {
        if (scale < 0) {
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = BigDecimal.valueOf(value1);
        BigDecimal b2 = BigDecimal.valueOf(value2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 　　* 四舍五入
     * 　　*
     * 　　* @param scale
     * 　　* 小数点后保留几位
     */

    public static double round(double v, int scale) throws IllegalAccessException {
        return div(v, 1, scale);
    }

}
