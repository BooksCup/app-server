package com.bc.app.server.utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.util.Random;

public class ImageBuilderUtils {

    private static BufferedImage image;
    private static int imageWidth = 300;  //图片的宽度
    private static int imageHeight = 300; //图片的高度

    public static void graphicsGeneration(String text) {
        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        //设置图片的背景色
        Graphics2D main = image.createGraphics();
        main.setColor(Color.white);
        main.fillRect(0, 0, imageWidth, imageHeight);
        Graphics graphics = image.createGraphics();
        //设置区域颜色
        Random rd = new Random();
        int flagNum = rd.nextInt(4) + 1;
        if (1 == flagNum) {
            graphics.setColor(new Color(124, 180, 216));
        } else if (2 == flagNum) {
            graphics.setColor(new Color(94, 207, 244));
        } else if (3 == flagNum) {
            graphics.setColor(new Color(191, 156, 221));
        } else if (4 == flagNum) {
            graphics.setColor(new Color(209, 175, 157));
        }
        //填充区域并确定区域大小位置
        graphics.fillRect(0, 0, imageWidth, imageHeight);
        //设置字体颜色，先设置颜色，再填充内容
        graphics.setColor(Color.white);
        if (text.length() > 2) {
            //设置字体
            Font font = new Font("宋体", Font.BOLD, 80);
            graphics.setFont(font);
            String[] split = text.split("");
            String str1 = "";
            String str2 = "";
            for (int i = 0; i < split.length; i++) {
                if (i < 2) {
                    str1 = str1 + split[i];
                } else {
                    str2 = str2 + split[i];
                }
            }
            for (int i = 0; i < 2; i++) {
                if (i == 0) {
                    FontMetrics fm = graphics.getFontMetrics();
                    graphics.drawString(str1, (imageWidth - fm.stringWidth(str1)) / 2, ((imageHeight - (fm.getHeight()) * 2) / 2) + fm.getAscent());  //画出水印
                } else {
                    FontMetrics fm = graphics.getFontMetrics();
                    graphics.drawString(str2, (imageWidth - fm.stringWidth(str1)) / 2, ((imageHeight - (fm.getHeight()) * 2) / 2) + fm.getAscent() + fm.getHeight());  //画出水印
                }
            }
            graphics.dispose();
        } else {
            //设置字体
            Font font = new Font("宋体", Font.BOLD, 120);
            graphics.setFont(font);
            //文字居中
            FontMetrics fm = graphics.getFontMetrics();
            graphics.drawString(text, (imageWidth - fm.stringWidth(text)) / 2, ((imageHeight - fm.getHeight()) / 2) + fm.getAscent());  //画出水印
            graphics.dispose();
        }
        createImage("f:\\hehe.jpg");

    }


    public static void createImage(String fileLocation) {
        BufferedOutputStream bos = null;
        if (image != null) {
            try {
                ImageIO.write(image, "png", new File(fileLocation));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
