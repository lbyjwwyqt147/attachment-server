package com.jwell.file.server.service;

import com.jwell.file.common.restful.RestfulVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/***
 * 文件名称: FlieDownloadService.java
 * 文件描述: 文件下载 service
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月27日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public interface FlieDownloadService {

    /**
     * 文件下载
     * @param request
     * @param response
     * @return
     */
    RestfulVo downloadFile(HttpServletRequest request, HttpServletResponse response);

    /**
     * 多个文件打包下载
     * @param request
     * @param response
     * @return
     */
    RestfulVo downloadZipFile(HttpServletRequest request, HttpServletResponse response);
}
