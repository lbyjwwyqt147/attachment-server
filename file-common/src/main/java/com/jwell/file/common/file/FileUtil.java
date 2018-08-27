package com.jwell.file.common.file;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/***
 *
 * @FileName: FileUtil
 * @Company:
 * @author    ljy
 * @Date      2018年08月24日
 * @version   1.0.0
 * @remark:   文件 工具类
 * @explain
 *
 *
 */
public class FileUtil {

    // 图片格式文件
    private static final String[] IMAGES = new String[]{".bmp", ".jpg", ".jpge", ".png", ".gif", ".psd", ".exif", ".tiff"};
    //文档格式文件
    private static final String[] DOCUMENTS = new String[]{".doc", ".xls", ".ppt", ".docx", ".xlsx", ".pptx", ".vsdx", ".pdm", ".txt", "pdf"};
    //视频格式文件
    private static final String[] VIDEOS = new String[]{".avi", ".rmvb", ".wmv", ".mov", ".mp4", ".ram", ".asf", ".rm"};
    private static final String ZIP = ".zip";

    /**
     * 得到文件大小(kb)
     * @param file
     * @return
     */
    public static Long getFileSize(File file){
        double kb = new BigDecimal(file.length() / 1024).setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
        System.out.println(kb);
        return file.length();
    }

    /**
     * 得到文件后缀名
     * @param fileName
     * @return
     */
    public static String getSuffixName(String fileName){
        // 文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        return suffixName;
    }

    /**
     * 得到文件类型(文档、图片、视频,ZIP)
     * @param suffixName  文件后缀名
     * @return
     */
    public static String getFileType(String suffixName){
        if (isImage(suffixName)){
            return FileEnum.IMAGES.name();
        }
        if (isDocument(suffixName)){
            return FileEnum.DOCUMENTS.name();
        }
        if (isVideo(suffixName)){
            return FileEnum.VIDEOS.name();
        }
        if (isZip(suffixName)){
            return FileEnum.ZIPS.name();
        }
        return FileEnum.OTHERS.name();
    }

    /**
     * 得到文件类型(文档、图片、视频,ZIP)
     * @param suffixName 文件后缀名
     * @return
     */
    public static FileEnum getFileTypeEnum(String suffixName){
        if (isImage(suffixName)){
            return FileEnum.IMAGES;
        }
        if (isDocument(suffixName)){
            return FileEnum.DOCUMENTS;
        }
        if (isVideo(suffixName)){
            return FileEnum.VIDEOS;
        }
        if (isZip(suffixName)){
            return FileEnum.ZIPS;
        }
        return FileEnum.OTHERS;
    }

    /**
     * 检测文件是否是图片类型
     * @param suffixName 文件后缀名
     * @return
     */
    public static boolean isImage(String suffixName){
        return Arrays.asList(IMAGES).contains(suffixName);
    }

    /**
     * 检测文件是否是文档类型
     * @param suffixName 文件后缀名
     * @return
     */
    public static  boolean isDocument(String suffixName){
        return Arrays.asList(DOCUMENTS).contains(suffixName);
    }

    /**
     * 检测文件是否是视频类型
     * @param suffixName 文件后缀名
     * @return
     */
    public static boolean isVideo(String suffixName){
        return  Arrays.asList(VIDEOS).contains(suffixName);
    }

    /**
     * 检测文件是否是zip类型
     * @param suffixName 文件后缀名
     * @return
     */
    public static boolean isZip(String suffixName){
        return suffixName.equals(ZIP);
    }


    /**
     * 转换路径（Windows 和 linux系统）
     * @param path
     * @return
     */
    public static String convertFilePath(String path) {
        String result = path;
        String fileSeparator = System.getProperty("file.separator");
        if (fileSeparator.equals("\\")) {
            result = path.replace("/", "\\");
        }
        return result;
    }

    /**
     * 重命名上传的文件名称
     * @param suffixName
     * @return
     */
    public static String getNewFileName(String suffixName){
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + System.nanoTime() + (int) (Math.random() * 100) + suffixName;
    }

    /**
     * 删除文件
     * @param path 文件路径
     */
    public static void  delete(String path){
        File file = new File(path);
        file.delete();
    }
}
