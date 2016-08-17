package com.demo.java.utils.image;

import java.io.File;

public class ImageCompressExample {

    public static void main(String[] args) throws Exception {
        System.out.println("开始：" + System.currentTimeMillis());
        File image = new File("FT.jpg");
        File resizeImage1 = new File("test1.jpg");
        File resizeImage2 = new File("test2.jpg");
        File resizeImage3 = new File("test3.jpg");
        File cutImage = new File("test4.jpg");
        ImageCompress.resize(image, resizeImage1, 500, 500);    //自定义宽高
        ImageCompress.resizeByWidth(image, resizeImage2, 500);  //自定义宽,高度Auto
        ImageCompress.resizeByHeight(image, resizeImage3, 500); //自定义高,宽度Auto
        ImageCompress.cutImage(image, cutImage, 500, 500, 300, 300);
        System.out.println("结束：" + System.currentTimeMillis());
    }
}
