package com.bc.app.server.controller;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.utils.PdfUtil;
import com.bc.app.server.utils.PlaceholderUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.HashMap;
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

            String template = templateBuffer.toString();
            Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
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
