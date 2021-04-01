package com.bc.app.server.controller;

import com.alibaba.fastjson.JSON;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.Contract;
import com.bc.app.server.entity.ContractDetail;
import com.bc.app.server.entity.ContractGoods;
import com.bc.app.server.entity.DeliveryTime;
import com.bc.app.server.enums.ContractTypeEnum;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.service.ContractService;
import com.bc.app.server.utils.BigDecimalUtil;
import com.bc.app.server.utils.MathUtil;
import com.bc.app.server.utils.PdfUtil;
import com.bc.app.server.utils.PlaceholderUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * pdf
 *
 * @author zhou
 */
@RestController
@RequestMapping("/pdf")
public class PdfController {

    private static final Logger logger = LoggerFactory.getLogger(PdfController.class);

    @Resource
    ContractService contractService;

    /**
     * 合同生成pdf
     *
     * @param contractId 合同ID
     * @return ResponseEntity<String>
     */
    @ApiOperation(value = "合同生成pdf", notes = "合同生成pdf")
    @PostMapping(value = "")
    public ResponseEntity<String> contractToPdf(@RequestParam String contractId) {
        ResponseEntity<String> responseEntity;
        FileOutputStream fileOutputStream = null;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/contract.html");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer templateBuffer = new StringBuffer();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                templateBuffer.append(data);
            }

            Contract contract = contractService.getContractById(contractId, "");

            String template = templateBuffer.toString();
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            paramMap.put("contractNo", contract.getContractNo());

            String contractType = ContractTypeEnum.getNameByCode(contract.getContractType());
            paramMap.put("contractType", StringUtils.isEmpty(contractType) ? " " : contractType);
            paramMap.put("signDate", contract.getSignDate());
            paramMap.put("fromName", StringUtils.isEmpty(contract.getFromEnterpriseName()) ? " " : contract.getFromEnterpriseName());
            paramMap.put("toName", StringUtils.isEmpty(contract.getToEnterpriseName()) ? " " : contract.getToEnterpriseName());
            paramMap.put("toEnterpriseAddress", StringUtils.isEmpty(contract.getToEnterpriseAddress()) ? " " : contract.getToEnterpriseAddress());
            paramMap.put("fromEnterpriseAddress", StringUtils.isEmpty(contract.getFromEnterpriseAddress()) ? " " : contract.getFromEnterpriseAddress());

            List<ContractDetail> contractDetailList = contract.getContractDetailList();
            StringBuffer detailBuffer = new StringBuffer();
            int index = 1;
            BigDecimal totalAmount = new BigDecimal(0.0);
            BigDecimal totalCount = new BigDecimal(0.0);

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

            String htmlContent = PlaceholderUtil.replace(template, paramMap);

            ByteArrayOutputStream byteArrayOutputStream = PdfUtil.html2pdf(htmlContent);

            ApplicationHome applicationHome = new ApplicationHome(getClass());
            File jarPath = applicationHome.getSource();
            logger.info("[contractToPdf], jarPath: " + jarPath);

            fileOutputStream = new FileOutputStream(jarPath + "/1.pdf");
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            responseEntity = new ResponseEntity<>(ResponseMsg.EXPORT_PDF_SUCCESS.getResponseCode(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.EXPORT_PDF_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            if (null != fileOutputStream) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responseEntity;
    }

}
