package com.bc.app.server.utils;

import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * pdf工具类
 *
 * @author zhou
 */
public class PdfUtil {

    /**
     *
     * @param url
     * @return
     * @throws Exception
     */
    public static ByteArrayOutputStream html2pdf(String url) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        System.out.println(url);
        renderer.setDocumentFromString(url);

        // 解决中文支持
        ITextFontResolver fontResolver = renderer.getFontResolver();
        if ("linux".equals(getCurrentOperatingSystem())) {
            fontResolver.addFont("/usr/share/fonts/chinese/SimSun.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } else {
            fontResolver.addFont("D:\\1_1111111\\simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }

        renderer.layout();
        renderer.createPDF(bos);
        bos.close();
        return bos;
    }

    public static String getCurrentOperatingSystem() {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println("---------当前操作系统是-----------" + os);
        return os;
    }

    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = null;
        try {
            StringBuffer sb = new StringBuffer();

            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\1_1111111\\1.html")));
            String data = null;
            while ((data = br.readLine()) != null) {
                System.out.println(data);
                sb.append(data);
            }

            String template = sb.toString();

            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("userName", "王五");
            paramMap.put("stockBizType", "办公用品入库");
            paramMap.put("goodsName", "笔");
            paramMap.put("applyNum", "13");
            paramMap.put("goodsUnit", "根");
            String result = PlaceholderUtil.replace(template, paramMap);

            ByteArrayOutputStream byteArrayOutputStream = html2pdf(result);
            fileOutputStream = new FileOutputStream("D://1_1111111/1.pdf");
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileOutputStream.close();
        }


    }

}
