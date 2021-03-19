package com.bc.app.server.utils;

import com.bc.app.server.entity.NumberSequence;
import com.bc.app.server.mapper.NumberSequenceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 通用工具类
 *
 * @author zhou
 */
public class CommonUtil {


    @Autowired
    public static NumberSequenceMapper numberSequenceMapper;

    /**
     * 生成主键
     *
     * @return 主键
     */
    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static String now() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 生成随机n位数
     *
     * @param len 随机数长度
     * @return 随机n位数
     */
    public static String generateRandomNum(int len) {
        if (len < 1) {
            return "";
        }
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, len - 1));
        return String.valueOf(rs);
    }

    /**
     * 计算失效时间
     *
     * @param period 时间间隔(单位秒)
     * @return 失效时间
     */
    public static String getExpireTime(long period) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date expireDate = new Date(System.currentTimeMillis() + period * 1000);
        return simpleDateFormat.format(expireDate);
    }

    /**
     * 获取时间戳
     *
     * @return 时间戳
     */
    public static String getOrderNo() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sf.format(new Date());
        return format;
    }


    public static synchronized String getOrderNostr(String code, String type, String enterpriseId) {
        // 初始值从“1”开始
        int num = 1;
        NumberSequence numberSequence = numberSequenceMapper.getByType(enterpriseId, type);
        // 第一次获取的数据是NULL
        if (numberSequence == null) {
            NumberSequence insertNumberSequence = new NumberSequence();
            insertNumberSequence.setId(CommonUtil.generateId());
            insertNumberSequence.setType(type);
            insertNumberSequence.setVal(1);
            insertNumberSequence.setEnterpriseId(enterpriseId);
            numberSequenceMapper.insert(insertNumberSequence);
        } else {
            num = numberSequence.getVal() + 1;
            numberSequenceMapper.updateVal(numberSequence.getId());
        }
        String n = String.valueOf(num);
        int max = 99;
        int min = 9;
        if (num <= max && num > min) {
            n = "0" + n;
        } else if (num <= min) {
            n = "00" + n;
        }
//        return new StringBuffer().append(CommonUtil.getTimeStr()).append(code).append(n).toString();
        return null;
    }

    /**
     * 获取jobNo
     *
     * @return 时间戳
     */
    public static String getJobNo() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        String format = sf.format(new Date()) + ((Math.random() * 9 + 1) * 100);
        return format;
    }

    /**
     * 转换"2019-11-05 11:16:46"字符串 ---》转化 "2019-11-05"字符串
     *
     * @return
     */
    public static String stringDateToResule(String dateStr) {
        SimpleDateFormat sb = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = "";
        try {
            if (!StringUtils.isEmpty(dateStr)) {
                Date parse = sb.parse(dateStr);
                strDate = sb.format(parse);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return strDate;
    }

}
