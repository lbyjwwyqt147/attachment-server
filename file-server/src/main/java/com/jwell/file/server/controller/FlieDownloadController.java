package com.jwell.file.server.controller;

import com.jwell.file.common.restful.RestfulVo;
import com.jwell.file.server.annotation.ApiVersion;
import com.jwell.file.server.service.FlieDownloadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@Api(tags = "文件下载相关的api")
@RestController
public class FlieDownloadController extends  BaseController{
    @Autowired
    private FlieDownloadService flieDownloadService;


    /**
     * 单个文件下载
     * @param id
     * @return
     */
    @ApiOperation(value = "下载单个文件", notes = "根据文件id下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", paramType = "path", required = true, dataType = "integer", defaultValue = "v1"),
            @ApiImplicitParam(name = "id", value = "文件id", paramType = "path", required = true, dataType = "integer")
    })
    @GetMapping(value = "/download/{id}")
    @ApiVersion(1)
    public RestfulVo downloadFile(@PathVariable(value = "id") Long id, HttpServletResponse response){
       return flieDownloadService.downloadFile(id, response);
    }
}
