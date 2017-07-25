/* 
 * @(#)CompressPicUtils.java    Created on 2013-8-16
 * Copyright (c) 2013 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import net.zdsoft.common.filesystem.util.FileSystemUtil;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2013-8-16 下午6:31:31 $
 */
public class ImageUtils {
    private static final String SEPERATOR = "/";

    /**
     * 进行图片的缩放,可以将原始路径（url）的图片缩放后放到另外的路径（newUrl）。如果 url和newUrl相同，那就是在原始图片的基础上做修改了
     * 
     * @param url
     *            要做修改的图片的物理路径，就是存放在服务器上的绝对路径
     * @param newWidth
     *            新的宽度
     * @param newHeight
     *            新的高度
     * @param newUrl
     *            要写入的路径，就是存放在服务器上的绝对路径，可以和url一样，这样就是对原始图片的修改了
     * @param suffix
     *            文件的后缀名，例如“jpg”
     * */
    public static void scaledImage(String imgsrc, int newWidth, int newHeight, String imgdest, String suffix)
            throws Exception {
        // 读取图片
        BufferedImage bi = ImageIO.read(new File(imgsrc));
        // 判断读入图片的宽和高
        if (bi.getHeight() > bi.getWidth()) {
            // 如果高比宽大,就交换两值,确保生成的图片的长个宽都在一个范围内
            int tmp = newWidth;
            newWidth = newHeight;
            newHeight = tmp;
        }
        // 用Image里的方法对图片进行等比压缩,只要宽和高其一值为负,则以正的那个值为最大边进行等比压缩
        Image image2 = bi.getScaledInstance(newWidth, newHeight, Image.SCALE_AREA_AVERAGING);
        // 获取压缩后图片的高和宽
        int height = image2.getHeight(null);
        int width = image2.getWidth(null);

        // 以新的高和宽构造一个新的缓存图片
        BufferedImage bi3 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi3.getGraphics();
        // 在新的缓存图片中画图,在画图的时候可以设定背景色
        g.drawImage(image2, 0, 0, Color.white, null);
        // 构造IO流输出到文件
        FileOutputStream fos = new FileOutputStream(new File(imgdest));
        // 将图片写入到指定的文件中
        ImageIO.write(bi3, suffix, fos);
        fos.close();

    }

    public static void scaledImage(File uploadedFile, String suffix) throws Exception {
        int maxWidth = 400;
        int maxHeight = 400;
        BufferedImage src = ImageIO.read(uploadedFile); // 读入文件
        int width = src.getWidth(); // 得到源图宽
        int height = src.getHeight(); // 得到源图长

        if (width > maxWidth || height > maxHeight) {
            if (width > height) {
                height = height * maxWidth / width;
                width = maxWidth;
            }
            else {
                width = width * maxHeight / height;
                height = maxHeight;
            }
        }
        else {
            return;
        }
        // 用Image里的方法对图片进行等比压缩,只要宽和高其一值为负,则以正的那个值为最大边进行等比压缩
        Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        // 获取压缩后图片的高和宽
        int imgWidth = image.getWidth(null);
        int imgHeight = image.getHeight(null);
        // 以新的高和宽构造一个新的缓存图片
        BufferedImage bi = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        // 在新的缓存图片中画图,在画图的时候可以设定背景色
        g.drawImage(image, 0, 0, null);
        g.dispose();
        // 构造IO流输出到文件
        FileOutputStream fos = new FileOutputStream(uploadedFile);
        // 将图片写入到指定的文件中
        ImageIO.write(bi, suffix, fos);
        fos.close();

    }

    /**
     * 将图片进行裁剪
     * 
     * @param imgsrc
     *            源图片文件路径
     * @param suffix
     *            文件的后缀
     * @param imgdest
     *            目标图片文件写入路径
     * @param x
     *            图片的x坐标
     * @param y
     *            图片的y坐标
     * @param width
     *            截取框的宽
     * @param height
     *            截取框的高
     * @throws IOException
     * */
    // 进行截取
    public static void cutImage(InputStream imgsrc, String imgdest, String suffix, int x, int y, int width, int height,
            int outputWidth, int outputHeight) throws Exception {
        // 去除时间问号
        imgdest = NetstudyFileUtils.deleteMark(imgdest);

        // 读取源图片文件
        BufferedImage sourceImage = null;
        try {
            sourceImage = ImageIO.read(imgsrc);

        }
        catch (Exception e) {
            e.printStackTrace();
            throw new Exception("读取源图片文件异常");
        }

        double scale = sourceImage.getWidth() > 750 ? sourceImage.getWidth() / 750.0 : 1.0;
        Image croppedImage;
        ImageFilter cropFilter;
        // 四个参数分别为图像起点坐标和宽高，即CropImageFilter(int x,int y,int width,int height)
        // 指定要裁剪的的文件的宽度和高度，以及起始坐标
        cropFilter = new CropImageFilter((int) (x * scale), (int) (y * scale), (int) (width * scale),
                (int) (height * scale));
        // 生成图片
        croppedImage = Toolkit.getDefaultToolkit().createImage(
                new FilteredImageSource(sourceImage.getSource(), cropFilter));

        // 获取创建后的图片的高度
        int h1 = croppedImage.getHeight(null);
        int w1 = croppedImage.getWidth(null);

        BufferedImage bi = new BufferedImage(w1, h1, BufferedImage.TYPE_INT_RGB);

        Graphics g = bi.getGraphics();
        // 在画图的时候可以设置背景色
        g.drawImage(croppedImage, 0, 0, Color.white, null);

        // 创建要存储图片的文件,如果文件存在就读取

        if (FileSystemUtil.fileExists(imgdest)) {
            FileSystemUtil.deleteFile(imgdest);
        }
        // 临时文件夹路径

        String tempFilePath = OSUtil.getTempDir() + SEPERATOR + imgdest;
        String path = tempFilePath.substring(0, tempFilePath.lastIndexOf(SEPERATOR));
        File tempFile = new File(path);

        FileUtils.forceMkdir(tempFile);// 强生成文件夹
        File t_files = new File(tempFilePath);
        // 创建文件输出流
        FileOutputStream fos = new FileOutputStream(t_files);
        // 将创建的图片写入到输出流
        ImageIO.write(bi, suffix, fos);
        fos.close();

        File t_file = new File(tempFilePath);

        imageCompress(t_file, tempFilePath, outputWidth, outputHeight, true);
        // 上传到服务器
        FileSystemUtil.saveFile(t_file, imgdest);
        // 删除临时文件夹路径中内容
        t_file.delete();
    }

    /**
     * 图片压缩
     * 
     * @param srcFile
     * @param destFile
     * @param outputWidth
     * @param outputHeight
     * @param proportion
     * @return
     */
    public static boolean imageCompress(File srcFile, String destFile, int outputWidth, int outputHeight,
            boolean proportion) {
        try {
            // 去除时间问号
            destFile = NetstudyFileUtils.deleteMark(destFile);

            // 获得源文件
            if (!srcFile.exists()) {
                return false;
            }
            Image img = ImageIO.read(srcFile);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                System.out.println(" can't read,retry!" + "<BR>");
                return false;
            }
            else {
                int newWidth;
                int newHeight;
                // 判断是否是等比缩放
                if (proportion == true) {
                    // 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getWidth(null)) / outputWidth;
                    double rate2 = ((double) img.getHeight(null)) / outputHeight;
                    // 根据缩放比率大的进行缩放控制
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    newWidth = (int) (img.getWidth(null) / rate);
                    newHeight = (int) (img.getHeight(null) / rate);
                }
                else {
                    newWidth = outputWidth; // 输出的图片宽度
                    newHeight = outputHeight; // 输出的图片高度
                }
                BufferedImage tag = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);

                /*
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
                 */
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                FileOutputStream out = new FileOutputStream(destFile);
                // JPEGImageEncoder可适用于其他图片类型的转换
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tag);
                out.close();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
