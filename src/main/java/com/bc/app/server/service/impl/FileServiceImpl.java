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

@Service("fileService")
public class FileServiceImpl implements FileService {

    public void getUploadUrl(String orgId, String imgStr) {
        try {
            String url = Constant.E_CONTRACT_BASE_URL + "/v1/files/getUploadUrl";
            //放置参数
            JSONObject jsobj = new JSONObject();
            //计算文件的md5值
            ByteArrayOutputStream bos = PdfUtil.html2pdf(imgStr);
            byte[] aa = bos.toByteArray();
            InputStream in = new ByteArrayInputStream(aa);

            String contentMd5 = GetKey.getStreamMD5(in);
            jsobj.put("contentMd5", contentMd5);
            //文件的MIME类型
            String contentType = "application/pdf";
            jsobj.put("contentType", contentType);
            //文件名称
            jsobj.put("fileName", "采购合同.pdf");
            //文件大小
            Integer fileSize = bos.size();
            jsobj.put("fileSize", fileSize);

            jsobj.put("convert2Pdf", false);
            jsobj.put("accountId", orgId);
            //请求api  返回的文件 id  文件上传的直传地址 uploadUrl
            ElectronicContractApiResult electronContractApi = ElectronicContractHttpUtil.httpPost(url, jsobj);
            //将返回回来的文件id存入自己的表
            if (electronContractApi.getCode() == 0) {
                String fileId = (String) electronContractApi.getData().get("fileId");
                String uploadUrl = (String) electronContractApi.getData().get("uploadUrl");
                System.out.println("uploadUrl:>>>>>>>>>>>" + uploadUrl);
                uploadFile(uploadUrl, contentType, contentMd5, aa);
//            addFlowDocument(orgId,sealId,flowId,fileId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void uploadFile(String uploadUrl, String contentType, String contentMd5, byte[] fileBytes) throws Exception {
        //放置参数
        HttpHead reqHeader = new HttpHead();
        reqHeader.setHeader("Content-MD5", contentMd5);
        reqHeader.setHeader("Content-Type", contentType);
        ElectronicContractHttpUtil.httpPutFile(uploadUrl, reqHeader, fileBytes);
    }

}
