package com.jwell.file.common.file;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
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
@Slf4j
public class FileUtil {

    // 图片格式文件
    private static final String[] IMAGES = new String[]{".bmp", ".jpg", ".jpge", ".png", ".gif", ".psd", ".exif", ".tiff"};
    //文档格式文件
    private static final String[] DOCUMENTS = new String[]{".doc", ".xls", ".ppt", ".docx", ".xlsx", ".pptx", ".vsdx", ".pdm", ".txt", ".pdf", ".wps", ".dps", ".et"};
    //视频格式文件
    private static final String[] VIDEOS = new String[]{".avi", ".rmvb", ".wmv", ".mov", ".mp4", ".ram", ".asf", ".rm"};
    private static final String ZIP = ".zip";

    /**
     * 得到文件大小(kb)
     * @param file
     * @return
     */
    public static String getFileSize(File file){
        DecimalFormat df1 = new DecimalFormat("0.00");
        String fileSize = null;
        long value = file.length();
        if (value < 1024) {
            fileSize = df1.format((double) value) + "B";
        } else if (value < 1048576) {
            fileSize = df1.format((double) value / 1024) + "KB";
        } else if (value < 1073741824) {
            fileSize = df1.format((double) value / 1048576) + "MB";
        } else {
            fileSize = df1.format((double) value / 1073741824) + "GB";
        }
        return fileSize;
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
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + System.nanoTime() + (int) (Math.random() * 1000) + suffixName;
    }

    /**
     * 删除文件
     * @param path 文件路径
     */
    public static void  delete(String path){
        log.info("删除文件路径：" + path);
        File file = new File(path);
        if (file.isFile() && file.exists()){
            file.delete();
            log.info(path + " 文件删除成功.");
        }
    }

    /**
     * 文件下载
     * @param filePath　　文件路径
     * @param fileName  文件名称
     * @param response
     */
    public static void downloadFile(String filePath, String fileName, HttpServletResponse response){
        File file = new File(filePath);
        //判断文件是否存在
        if (file.exists()) {
            response.reset();
            response.setContentType("application/octet-stream");
            try {
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            //文件输入流
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                int len;
                while((len = fis.read(buffer))>0) {
                    response.getOutputStream().write(buffer,0,len);
                }
            } catch (Exception e) {
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }finally{
                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
