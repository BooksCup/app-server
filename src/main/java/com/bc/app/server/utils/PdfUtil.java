package com.bc.app.server.utils;

import com.bc.app.server.cons.Constant;
import com.itextpdf.text.pdf.BaseFont;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

/**
 * pdf工具类
 *
 * @author zhou
 */
public class PdfUtil {

    /**
     * 网页转pdf
     *
     * @param htmlContent 网页内容(带html标签)
     * @return pdf字节输出流
     * @throws Exception 异常
     */
    public static ByteArrayOutputStream html2pdf(String htmlContent) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        ITextFontResolver fontResolver = renderer.getFontResolver();
        if (OsUtil.LINUX) {
            // linux
            fontResolver.addFont(Constant.FONT_PATH_LINUX, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } else if (OsUtil.WINDOWS) {
            fontResolver.addFont(Constant.FONT_PATH_WINDOWS, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        } else {
            fontResolver.addFont(Constant.FONT_PATH_WINDOWS, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        }

        renderer.layout();
        renderer.createPDF(bos);
        bos.close();
        return bos;
    }

}
