package com.jwell.file.server.controller;

import com.jwell.file.common.restful.RestfulVo;
import com.jwell.file.server.annotation.ApiVersion;
import com.jwell.file.server.service.FlieDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/***
 * 文件名称: FlieDownloadController.java
 * 文件描述: 文件下载 Controller
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月28日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@RestController
public class FlieDownloadController extends  BaseController{
    @Autowired
    private FlieDownloadService flieDownloadService;

    /**
     * 文件下载
     * @param id
     * @return
     */
    @GetMapping(value = "/download/{id}")
    @ApiVersion(1)
    public RestfulVo downloadFile(@PathVariable(value = "id") Long id, HttpServletResponse response){
       return flieDownloadService.downloadFile(id, response);
    }
}
