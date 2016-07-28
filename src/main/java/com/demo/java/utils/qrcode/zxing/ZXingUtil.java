package com.demo.java.utils.qrcode.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * 使用Google ZXing工具生成二维码
 */
public class ZXingUtil {

    static int width = 140;
    static int height = 140;

    /**
     * 生成二维码图片
     *
     * @param content   内容
     * @param width     宽度
     * @param height    高度
     * @param format    格式(PNG,JPG)
     * @param imageName 图片名称
     * @param src       图片路径
     * @throws IOException
     * @throws WriterException
     */
    public static void encode(String content, int width, int height, String format, String imageName, String src) throws IOException, WriterException {
        BitMatrix bitMatrix = getBitMatrix(content, width, height);
        File file = new File(src + "/" + imageName + "." + format);
        MatrixToImageWriter.write2File(bitMatrix, format, file);
    }

    /**
     * 返回二维码BitMatrix
     *
     * @param content 内容
     * @param width   宽度
     * @param height  高度
     * @return
     * @throws WriterException
     */
    public static BitMatrix getBitMatrix(String content, int width, int height) throws WriterException {
        // 用于设置QR二维码参数
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        // 设置QR二维码的纠错级别——这里选择最高H级别
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 参数顺序分别为：编码内容，编码类型，生成图片宽度，生成图片高度，设置参数
        return new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
    }

    /**
     * 返回二维码BufferedImage
     *
     * @param content 内容
     * @param width   宽度
     * @param height  高度
     * @return
     * @throws WriterException
     */
    public static BufferedImage getBufferedImage(String content, int width, int height) throws WriterException {
        BitMatrix bitMatrix = getBitMatrix(content, width, height);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }

    /**
     * 返回二维码BufferedImage
     *
     * @param content 内容
     * @return
     * @throws WriterException
     */
    public static BufferedImage getBufferedImage(String content) throws WriterException {
        return getBufferedImage(content, width, height);
    }
}
