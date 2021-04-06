package com.bc.app.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.ElectronicContractApiResult;
import com.bc.app.server.service.FileService;
import com.bc.app.server.utils.ElectronicContractHttpUtil;
import com.bc.app.server.utils.GetKey;
import com.bc.app.server.utils.PdfUtil;
import org.apache.http.client.methods.HttpHead;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * 文件
 *
 * @author zhou
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    /**
     * 生成合同的pdf文件，通过上传方式在e签宝创建文件
     *
     * @param htmlContent 网页内容(带html标签)
     * @return 文件id
     * @throws Exception 异常
     */
    @Override
    public String uploadFileToSignPlatform(String htmlContent) throws Exception {
        String fileId = "";
        String url = Constant.E_CONTRACT_BASE_URL + "/v1/files/getUploadUrl";
        JSONObject paramJson = new JSONObject();
        ByteArrayOutputStream bos = PdfUtil.html2pdf(htmlContent);
        byte[] pdfFileByteArray = bos.toByteArray();
        InputStream in = new ByteArrayInputStream(pdfFileByteArray);

        String contentMd5 = GetKey.getStreamMD5(in);
        paramJson.put("contentMd5", contentMd5);
        // 文件的MIME类型
        String contentType = "application/pdf";
        paramJson.put("contentType", contentType);
        // 文件名称
        paramJson.put("fileName", "采购合同.pdf");
        // 文件大小
        Integer fileSize = bos.size();
        paramJson.put("fileSize", fileSize);

        paramJson.put("convert2Pdf", false);
        ElectronicContractApiResult electronContractApi = ElectronicContractHttpUtil.httpPost(url, String.valueOf(paramJson));
        if (electronContractApi.getCode() == 0) {
            fileId = (String) electronContractApi.getData().get("fileId");
            String uploadUrl = (String) electronContractApi.getData().get("uploadUrl");
            HttpHead httpHead = new HttpHead();
            httpHead.setHeader("Content-MD5", contentMd5);
            httpHead.setHeader("Content-Type", contentType);
            ElectronicContractHttpUtil.httpPutFile(uploadUrl, httpHead, pdfFileByteArray);
        }
        return fileId;
    }

}
