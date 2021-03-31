package com.bc.app.server.utils;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetKey {
    private static Logger LOG = LoggerFactory.getLogger(GetKey.class);


    public static byte[] getStreamMD5Bytes128(InputStream stream) {
        byte[] md5Bytes = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = stream.read(buffer, 0, 1024)) != -1) {
                md5.update(buffer, 0, length);
            }
            md5Bytes = md5.digest();
            stream.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return md5Bytes;
    }

    /**
     *  1.图片64位转图片
     *  2.图片上传
     *  3.图片转pdf
     *  4.获取pdf MD5加密
     * @param in
     * @return
     * @throws Exception
     */
    public static String getStreamMD5(InputStream in) throws Exception{
        byte[] bytes = GetKey.getStreamMD5Bytes128(in);
        // 对文件MD5的二进制数组进行base64编码（而不是对32位的16进制字符串进行编码）
        return new String(Base64.encodeBase64(bytes));
    }
}
