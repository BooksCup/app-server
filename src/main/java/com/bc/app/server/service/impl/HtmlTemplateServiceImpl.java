package com.bc.app.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.Contract;
import com.bc.app.server.entity.ContractDetail;
import com.bc.app.server.entity.ContractGoods;
import com.bc.app.server.entity.DeliveryTime;
import com.bc.app.server.enums.ContractTypeEnum;
import com.bc.app.server.service.HtmlTemplateService;
import com.bc.app.server.utils.BigDecimalUtil;
import com.bc.app.server.utils.MathUtil;
import com.bc.app.server.utils.PlaceholderUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 网页模板
 *
 * @author zhou
 */
@Service("htmlTemplateService")
public class HtmlTemplateServiceImpl implements HtmlTemplateService {

    /**
     * 获取合同网页模板
     *
     * @param contract 合同
     * @return 合同网页模板
     * @throws Exception 异常
     */
    @Override
    public String getContractHtmlTemplate(Contract contract) throws Exception {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/contract.html");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer templateBuffer = new StringBuffer();
        String data;
        while ((data = bufferedReader.readLine()) != null) {
            templateBuffer.append(data);
        }

        String template = templateBuffer.toString();
        Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        paramMap.put("contractNo", contract.getContractNo());

        String contractType = ContractTypeEnum.getNameByCode(contract.getContractType());
        paramMap.put("contractType", StringUtils.isEmpty(contractType) ? " " : contractType);
        paramMap.put("signDate", StringUtils.isEmpty(contract.getSignDate()) ? " " : contract.getSignDate());
        paramMap.put("fromName", StringUtils.isEmpty(contract.getFromEnterpriseName()) ? " " : contract.getFromEnterpriseName());
        paramMap.put("toName", StringUtils.isEmpty(contract.getToEnterpriseName()) ? " " : contract.getToEnterpriseName());
        paramMap.put("toEnterpriseAddress", StringUtils.isEmpty(contract.getToEnterpriseAddress()) ? " " : contract.getToEnterpriseAddress());
        paramMap.put("fromEnterpriseAddress", StringUtils.isEmpty(contract.getFromEnterpriseAddress()) ? " " : contract.getFromEnterpriseAddress());

        List<ContractDetail> contractDetailList = contract.getContractDetailList();
        StringBuffer detailBuffer = new StringBuffer();
        int index = 1;
        BigDecimal totalAmount = new BigDecimal("0.0");
        BigDecimal totalCount = new BigDecimal("0.0");

        for (ContractDetail contractDetail : contractDetailList) {
            ContractGoods contractGoods = JSON.parseObject(contractDetail.getSourceJson(), ContractGoods.class);
            List<DeliveryTime> deliveryTimeList = contractGoods.getDeliveryTimeList();
            String deliveryDate = " ";
            if (!CollectionUtils.isEmpty(deliveryTimeList)) {
                deliveryDate = deliveryTimeList.get(0).getDeliveryTime();
            }
            String price = new BigDecimal(contractGoods.getExt().getGoodsPrice()).stripTrailingZeros().toPlainString();
            String count = new BigDecimal(contractDetail.getThisCount()).stripTrailingZeros().toPlainString();
            BigDecimal amount = BigDecimalUtil.multiply(price, count);
            detailBuffer.append("<tr>\n" +
                    "                <td>\n" +
                    "                    <span>" + index + "</span>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <p style=\"width: 70px; word-break: break-all\">\n" +
                    "                        <span style=\"display: block\">" + contractGoods.getGoodsNo() + "</span>\n" +
                    "                        <span></span>\n" +
                    "                    </p>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <span>" + contractGoods.getGoodsName() + "</span>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <span>" + count + "</span>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <span>" + contractGoods.getGoodsUnit() + "</span>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <span>" + price + "</span>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <span>" + amount.stripTrailingZeros().toPlainString() + "</span>\n" +
                    "                </td>\n" +
                    "                <td>\n" +
                    "                    <span>" + deliveryDate + "</span>\n" +
                    "                </td>\n" +
                    "                <td></td>\n" +
                    "            </tr>");
            totalCount = BigDecimalUtil.add(totalCount, count);
            totalAmount = BigDecimalUtil.add(totalAmount, amount);
        }
        paramMap.put("contractGoods", detailBuffer.toString());
        paramMap.put("totalCount", totalCount.stripTrailingZeros().toPlainString());
        paramMap.put("totalAmount", totalAmount.stripTrailingZeros().toPlainString());
        paramMap.put("totalAmountChinese", MathUtil.numToChinese(totalAmount.toString()));

        return PlaceholderUtil.replace(template, paramMap);
    }

}
