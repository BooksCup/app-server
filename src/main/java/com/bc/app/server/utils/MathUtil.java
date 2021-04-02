package com.bc.app.server.utils;

/**
 * 计算工具类
 *
 * @author zhou
 */
public class MathUtil {

    private static final String CHINESE_DIGIT = "零壹贰叁肆伍陆柒捌玖";
    private static final String CHINESE_UNIT = "分角整元拾佰仟万拾佰仟亿拾佰仟";

    public static String numToChinese(String inputNum) {
        String temp = "";
        String result = "";
        if (inputNum == null) {
            return "输入字串不是数字串只能包括以下字符（'0'～'9'，'.')，输入字串最大只能精确到仟亿，小数点只能两位！";
        }
        temp = inputNum.trim();
        float f;
        try {
            f = Float.parseFloat(temp);
        } catch (Exception e) {
            return "输入字串不是数字串只能包括以下字符（'0'～'9'，'.')，输入字串最大只能精确到仟亿，小数点只能两位！";
        }
        int len = 0;
        if (temp.indexOf(".") == -1) {
            len = temp.length();
        } else {
            len = temp.indexOf(".");
        }
        if (len > CHINESE_UNIT.length() - 3) {
            return ("输入字串最大只能精确到仟亿，小数点只能两位！");
        }
        int n1, n2 = 0;
        String num = "";
        String unit = "";
        for (int i = 0; i < temp.length(); i++) {
            if (i > len + 2) {
                break;
            }
            if (i == len) {
                continue;
            }
            n1 = Integer.parseInt(String.valueOf(temp.charAt(i)));
            num = CHINESE_DIGIT.substring(n1, n1 + 1);
            n1 = len - i + 2;
            unit = CHINESE_UNIT.substring(n1, n1 + 1);
            result = result.concat(num).concat(unit);
        }
        if ((len == temp.length()) || (len == temp.length() - 1)) {
            result = result.concat("整");
        }
        if (len == temp.length() - 2) {
            result = result.concat("零分");
        }
        return result;
    }

    public static void main(String[] args) {
        String string = "1111123576.3200000";
        System.out.println(numToChinese(string));
    }
}
