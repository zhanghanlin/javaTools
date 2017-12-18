package com.demo.java.utils.image;

import com.mortennobel.imagescaling.ResampleOp;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * 图片压缩
 *
 * @author zhanghanlin
 */
public class ImageCompress {

    /**
     * 根据宽度缩放
     *
     * @param image
     * @param resizeImage
     * @param w
     */
    public static void resizeByWidth(File image, File resizeImage, int w) {
        int[] wh = getWH(image);
        resize(image, resizeImage, w, wh[1] * w / wh[0]);
    }

    /**
     * 根据高度缩放
     *
     * @param image
     * @param resizeImage
     * @param h
     */
    public static void resizeByHeight(File image, File resizeImage, int h) {
        int[] wh = getWH(image);
        resize(image, resizeImage, wh[0] * h / wh[1], h);
    }

    /**
     * 压缩图片到固定的大小
     *
     * @param image
     * @param resizeImage
     * @param w
     * @param h
     */
    public static void resize(File image, File resizeImage, int w, int h) {
        BufferedImage inputBufImage;
        try {
            inputBufImage = ImageIO.read(image);
            ResampleOp resampleOp = new ResampleOp(w, h);
            BufferedImage rescaledTomato = resampleOp.filter(inputBufImage, null);
            ImageIO.write(rescaledTomato, getFormatName(image), resizeImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 裁剪图片
     *
     * @param image    需要裁剪的图片
     * @param cutImage 裁剪后的图片
     * @param x        裁剪的X坐标
     * @param y        裁剪的Y左边
     * @param width    裁剪的宽度
     * @param height   裁剪的高度
     */
    public static void cutImage(File image, File cutImage, int x, int y, int width, int height) {
        ImageInputStream inputStream = null;
        try {
            inputStream = ImageIO.createImageInputStream(image);
            Iterator<ImageReader> iterator = ImageIO.getImageReaders(inputStream);
            ImageReader reader = iterator.next();
            reader.setInput(inputStream, true);
            ImageReadParam param = reader.getDefaultReadParam();
            Rectangle rectangle = new Rectangle(x, y, width, height);
            param.setSourceRegion(rectangle);
            BufferedImage bi = reader.read(0, param);
            ImageIO.write(bi, getFormatName(image), cutImage);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 图片后缀名
     *
     * @param file
     * @return
     */
    static String getFormatName(File file) {
        return file.getName().substring(file.getName().lastIndexOf(".") + 1);
    }

    /**
     * 得到图片宽高
     *
     * @param image
     * @return
     */
    static int[] getWH(File image) {
        int[] wh = new int[2];
        Image img = null;
        try {
            img = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        wh[0] = img.getWidth(null);
        wh[1] = img.getHeight(null);
        return wh;
    }
}
