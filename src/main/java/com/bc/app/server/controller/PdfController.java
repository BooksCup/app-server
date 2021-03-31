package com.bc.app.server.controller;

import com.bc.app.server.enums.ResponseMsg;
import com.bc.app.server.utils.PdfUtil;
import com.bc.app.server.utils.PlaceholderUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.sl.usermodel.Placeholder;
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

    @ApiOperation(value = "合同生成pdf", notes = "合同生成pdf")
    @PostMapping(value = "")
    public ResponseEntity<String> contractToPdf(@RequestParam String contractId) {
        ResponseEntity<String> responseEntity;
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("template/contract.html");
//            InputStream inputStream = new FileInputStream(new File("/home/app-server/1.html"));
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer htmlContentBuffer = new StringBuffer();
            String data;
            while ((data = bufferedReader.readLine()) != null) {
                htmlContentBuffer.append(data);
            }

            String template = htmlContentBuffer.toString();
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("userName", "王二麻子");
            String result = PlaceholderUtil.replace(template, paramMap);

            ByteArrayOutputStream byteArrayOutputStream = PdfUtil.html2pdf(result);
            FileOutputStream fileOutputStream = new FileOutputStream("D://1.pdf");
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            responseEntity = new ResponseEntity<>( System.getProperty("os.name").toLowerCase(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(ResponseMsg.PUSH_ERROR.getResponseCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
