package com.bc.app.server.service;

/**
 * 文件
 *
 * @author zhou
 */
public interface FileService {

    /**
     * 生成合同的pdf文件，通过上传方式在e签宝创建文件
     *
     * @param htmlContent 网页内容(带html标签)
     * @return 文件id
     * @throws Exception 异常
     */
    String uploadFileToSignPlatform(String htmlContent) throws Exception;

}
