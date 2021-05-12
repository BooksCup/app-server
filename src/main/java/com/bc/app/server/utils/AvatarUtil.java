package com.bc.app.server.utils;

import com.bc.app.server.cons.Constant;
import com.bc.app.server.entity.OssConfig;
import org.apache.commons.lang.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 头像工具类
 *
 * @author zhou
 */
public class AvatarUtil {

    /**
     * 图片的间隙
     */
    private static final int SIDE = 6;

    /**
     * 画板尺寸
     */
    private static final int CANVAS_W = 112;
    private static final int CANVAS_H = 112;

    /**
     * 尺寸1(小)
     */
    private static final int IMAGE_SIZE_SMALL = CANVAS_H - (2 * SIDE);
    /**
     * 尺寸2(中)
     */
    private static final int IMAGE_SIZE_MEDIUM = (CANVAS_H - (3 * SIDE)) / 2;
    /**
     * 尺寸3(大)
     */
    private static final int IMAGE_SIZE_LARGE = (CANVAS_H - (4 * SIDE)) / 3;

    private static final String FORMAT = "jpg";

    /**
     * 生成合成头像
     *
     * @param pathList  路径列表
     * @param ossConfig OSS配置
     * @return 合成头像OSS地址
     * @throws IOException 异常
     */
    public static String getCombinationAvatar(List<String> pathList, OssConfig ossConfig)
            throws IOException {
        List<BufferedImage> bufferedImages = new ArrayList<>();
        int imageSize;
        if (pathList.size() <= 1) {
            // 1张图片
            imageSize = IMAGE_SIZE_SMALL;
        } else if (pathList.size() > 1 && pathList.size() < 5) {
            // 2-4张图片
            imageSize = IMAGE_SIZE_MEDIUM;
        } else {
            // >=5张图片
            imageSize = IMAGE_SIZE_LARGE;
        }

        for (int i = 0; i < pathList.size(); i++) {
            BufferedImage resize2 = resize2(pathList.get(i), imageSize, imageSize, true);
            bufferedImages.add(resize2);
        }

        BufferedImage outImage = new BufferedImage(CANVAS_W, CANVAS_H, BufferedImage.TYPE_INT_RGB);

        // 生成画布
        Graphics g = outImage.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        // 设置背景色
        g2d.setBackground(new Color(231, 231, 231));
        // 通过使用当前绘图表面的背景色进行填充来清除指定的矩形。
        g2d.clearRect(0, 0, CANVAS_W, CANVAS_H);
        // 开始拼凑 根据图片的数量判断该生成那种样式的组合头像目前为九种
        for (int i = 1; i <= bufferedImages.size(); i++) {
            Integer size = bufferedImages.size();
            switch (size) {
                case 1:
                    g2d.drawImage(bufferedImages.get(i - 1), SIDE, SIDE, null);
                    break;
                case 2:
                    if (i == 1) {
                        g2d.drawImage(bufferedImages.get(i - 1), SIDE, (CANVAS_W - imageSize) / 2, null);
                    } else {
                        g2d.drawImage(bufferedImages.get(i - 1), 2 * SIDE + imageSize, (CANVAS_W - imageSize) / 2, null);
                    }
                    break;
                case 3:
                    if (i == 1) {
                        g2d.drawImage(bufferedImages.get(i - 1), (CANVAS_W - imageSize) / 2, SIDE, null);
                    } else {
                        g2d.drawImage(bufferedImages.get(i - 1), (i - 1) * SIDE + (i - 2) * imageSize, imageSize + (2 * SIDE), null);
                    }
                    break;
                case 4:
                    if (i <= 2) {
                        g2d.drawImage(bufferedImages.get(i - 1), i * SIDE + (i - 1) * imageSize, SIDE, null);
                    } else {
                        g2d.drawImage(bufferedImages.get(i - 1), (i - 2) * SIDE + (i - 3) * imageSize, imageSize + 2 * SIDE, null);
                    }
                    break;
                case 5:
                    if (i <= 2) {
                        g2d.drawImage(bufferedImages.get(i - 1), (CANVAS_W - 2 * imageSize - SIDE) / 2 + (i - 1) * imageSize + (i - 1) * SIDE, (CANVAS_W - 2 * imageSize - SIDE) / 2, null);
                    } else {
                        g2d.drawImage(bufferedImages.get(i - 1), (i - 2) * SIDE + (i - 3) * imageSize, ((CANVAS_W - 2 * imageSize - SIDE) / 2) + imageSize + SIDE, null);
                    }
                    break;
                case 6:
                    if (i <= 3) {
                        g2d.drawImage(bufferedImages.get(i - 1), SIDE * i + imageSize * (i - 1), (CANVAS_W - 2 * imageSize - SIDE) / 2, null);
                    } else {
                        g2d.drawImage(bufferedImages.get(i - 1), ((i - 3) * SIDE) + ((i - 4) * imageSize), ((CANVAS_W - 2 * imageSize - SIDE) / 2) + imageSize + SIDE, null);
                    }
                    break;
                case 7:
                    if (i <= 1) {
                        g2d.drawImage(bufferedImages.get(i - 1), 2 * SIDE + imageSize, SIDE, null);
                    }
                    if (i <= 4 && i > 1) {
                        g2d.drawImage(bufferedImages.get(i - 1), ((i - 1) * SIDE) + ((i - 2) * imageSize), 2 * SIDE + imageSize, null);
                    }
                    if (i <= 7 && i > 4) {
                        g2d.drawImage(bufferedImages.get(i - 1), ((i - 4) * SIDE) + ((i - 5) * imageSize), 3 * SIDE + 2 * imageSize, null);
                    }
                    break;
                case 8:
                    if (i <= 2) {
                        g2d.drawImage(bufferedImages.get(i - 1), (CANVAS_W - 2 * imageSize - SIDE) / 2
                                + (i - 1) * imageSize + (i - 1) * SIDE, SIDE, null);
                    }
                    if (i <= 5 && i > 2) {
                        g2d.drawImage(bufferedImages.get(i - 1), ((i - 2) * SIDE) + ((i - 3) * imageSize), 2 * SIDE + imageSize, null);
                    }
                    if (i <= 8 && i > 5) {
                        g2d.drawImage(bufferedImages.get(i - 1), ((i - 5) * SIDE) + ((i - 6) * imageSize), 3 * SIDE + 2 * imageSize, null);
                    }
                    break;
                case 9:
                    if (i <= 3) {
                        g2d.drawImage(bufferedImages.get(i - 1), (i * SIDE) + ((i - 1) * imageSize), SIDE, null);
                    }
                    if (i <= 6 && i > 3) {
                        g2d.drawImage(bufferedImages.get(i - 1), ((i - 3) * SIDE) + ((i - 4) * imageSize), 2 * SIDE + imageSize, null);
                    }
                    if (i <= 9 && i > 6) {
                        g2d.drawImage(bufferedImages.get(i - 1), ((i - 6) * SIDE) + ((i - 7) * imageSize), 3 * SIDE + 2 * imageSize, null);
                    }
                    break;
                default:
                    break;
            }
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(outImage, FORMAT, os);
        // 取得当前上传文件的文件名称
        String fileName = CommonUtil.generateId() + '.' + FORMAT;

        String result = OssUtil.uploadFile(os.toByteArray(), fileName, ossConfig);
        if (StringUtils.isNotEmpty(result)) {
            return ossConfig.getDomain() + fileName;
        }
        return null;
    }

    /**
     * 图片缩放
     *
     * @param filePath    图片路径
     * @param height      高度
     * @param width       宽度
     * @param paddingFlag 比例不对时是否需要补白
     */
    public static BufferedImage resize2(String filePath, int height, int width,
                                        boolean paddingFlag) {
        try {
            // 缩放比例
            double ratio;
            BufferedImage bufferedImage;
            if (filePath.toLowerCase().startsWith(Constant.PROTOCOL_HTTP_PREFIX) ||
                    filePath.toLowerCase().startsWith(Constant.PROTOCOL_HTTPS_PREFIX)) {
                bufferedImage = ImageIO.read(new URL(filePath));
            } else {
                bufferedImage = ImageIO.read(new File(filePath));
            }
            Image tempImage = bufferedImage.getScaledInstance(width, height,
                    Image.SCALE_SMOOTH);
            // 计算比例
            if ((bufferedImage.getHeight() > height) || (bufferedImage.getWidth() > width)) {
                if (bufferedImage.getHeight() > bufferedImage.getWidth()) {
                    ratio = (new Integer(height)).doubleValue()
                            / bufferedImage.getHeight();
                } else {
                    ratio = (new Integer(width)).doubleValue() / bufferedImage.getWidth();
                }
                AffineTransformOp op = new AffineTransformOp(
                        AffineTransform.getScaleInstance(ratio, ratio), null);
                tempImage = op.filter(bufferedImage, null);
            }
            if (paddingFlag) {
                BufferedImage image = new BufferedImage(width, height,
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D g = image.createGraphics();
                g.setColor(Color.white);
                g.fillRect(0, 0, width, height);
                if (width == tempImage.getWidth(null)) {
                    g.drawImage(tempImage, 0, (height - tempImage.getHeight(null)) / 2,
                            tempImage.getWidth(null), tempImage.getHeight(null),
                            Color.white, null);
                } else {
                    g.drawImage(tempImage, (width - tempImage.getWidth(null)) / 2, 0,
                            tempImage.getWidth(null), tempImage.getHeight(null),
                            Color.white, null);
                }
                g.dispose();
                tempImage = image;
            }
            return (BufferedImage) tempImage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}