package com.bc.app.server.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author qiu
 * 出入库申请 biz_type`'业务类型 枚举
 */
@Getter
@AllArgsConstructor
public enum StockApplicationBizTypeEnum {
    CODE_1("1", "成品销售"), CODE_2("2", "材料销售"), CODE_3("3", "成品入库"),
    CODE_4("4","材料入库"), CODE_5("5","生产领用"),CODE_6("6","完工入库"),
    CODE_7("7","固定资产入库"),CODE_8("8","办公用品入库"),CODE_9("9","退货入库"),
    CODE_10("10","固定资产领用"),CODE_11("11","办公用品领用"),CODE_12("12","固定资产处理");

    String value;
    String desc;

    public static StockApplicationBizTypeEnum getEnumByValue(String value) {
        StockApplicationBizTypeEnum[] arr$ = values();
        int len$ = arr$.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            StockApplicationBizTypeEnum t = arr$[i$];
            if (!StringUtils.isEmpty(value)&&value.equals(t.getValue())) {
                return t;
            }
        }
        return null;
    }
}
