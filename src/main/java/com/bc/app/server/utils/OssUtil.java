package com.bc.app.server.utils;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectResult;
import com.bc.app.server.entity.OssConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * OSS工具类
 *
 * @author zhou
 */
public class OssUtil {

    private static final Logger logger = LoggerFactory.getLogger(OssUtil.class);

    /**
     * 上传文件
     *
     * @param file      文件
     * @param key       文件名
     * @param ossConfig oss配置
     * @return 上传成功的标签
     */
    public static String uploadFile(MultipartFile file, String key, OssConfig ossConfig) {
        String eTag = "";
        OSSClient client = new OSSClient(ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        try {
            InputStream is = new ByteArrayInputStream(file.getBytes());
            // 上传图片
            PutObjectResult result = client.putObject(ossConfig.getBucketName(), key, is);
            eTag = result.getETag();
        } catch (Exception e) {
            logger.error("OSS错误=>", e);
        } finally {
            // 关闭OSS服务,一定要关闭
            client.shutdown();
        }
        return eTag;
    }

    /**
     * 上传文件
     *
     * @param bytes     字节数组
     * @param key       文件名
     * @param ossConfig oss配置
     * @return 上传成功的标签
     */
    public static String uploadFile(byte[] bytes, String key, OssConfig ossConfig) {
        String eTag = "";
        OSSClient client = new OSSClient(ossConfig.getEndpoint(),
                ossConfig.getAccessKeyId(), ossConfig.getAccessKeySecret());
        try {
            InputStream is = new ByteArrayInputStream(bytes);
            // 上传图片
            PutObjectResult result = client.putObject(ossConfig.getBucketName(), key, is);
            eTag = result.getETag();
        } catch (Exception e) {
            logger.error("OSS错误=>", e);
        } finally {
            // 关闭OSS服务,一定要关闭
            client.shutdown();
        }
        return eTag;
    }

}
