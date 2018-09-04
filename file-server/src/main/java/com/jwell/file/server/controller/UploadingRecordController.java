package com.jwell.file.server.controller;

import com.jwell.file.common.restful.RestfulVo;
import com.jwell.file.common.restful.ResultUtil;
import com.jwell.file.common.util.SystemUtils;
import com.jwell.file.server.annotation.ApiVersion;
import com.jwell.file.server.service.AttachmentUploadingRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/***
 * 文件名称: UploadingRecordController.java
 * 文件描述: 文件管理 Controller
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月28日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Api(tags = "文件管理相关API")
@RestController
public class UploadingRecordController extends BaseController {
    @Autowired
    private AttachmentUploadingRecordService attachmentUploadingRecordService;

    /**
     * 单条删除数据
     * @param id
     * @return
     */
    @ApiOperation(value = "单条删除数据",notes = "适用于单条删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", paramType = "path", required = true, dataType = "integer", defaultValue = "v1"),
            @ApiImplicitParam(name = "id", value = "文件id", paramType = "path", required = true, dataType = "integer")
    })
    @DeleteMapping(value = "/delete/{id}")
    @ApiVersion(1)
    public RestfulVo singleDelete(@PathVariable(value = "id") Long id){
        if (attachmentUploadingRecordService.deleteById(id) > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }

    /**
     * 批量删除
     * @param id　 多个id 用 , 隔开
     * @return
     */
    @ApiOperation(value = "删除多条数据",notes = "适用于批量删除数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", paramType = "path", required = true, dataType = "integer", defaultValue = "v1"),
            @ApiImplicitParam(name = "id", value = "文件id,多个id 用,隔开", paramType = "path", required = true, dataType = "string")
    })
    @DeleteMapping(value = "/batchDelete/{id}")
    @ApiVersion(1)
    public RestfulVo batchDelete(@PathVariable(value = "id") String id){
        if (attachmentUploadingRecordService.deleteByIdIn(SystemUtils.getIds(id)) > 0) {
            return ResultUtil.success();
        }
        return ResultUtil.fail();
    }

    /**
     * 根据　一组　id　获取信息
     * @param id  多个id 用 , 隔开
     * @return
     */
    @ApiOperation(value = "根据文件id获取信息",notes = "适用于根据文件id获取信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "version", value = "版本号", paramType = "path", required = true, dataType = "integer", defaultValue = "v1"),
            @ApiImplicitParam(name = "id", value = "文件id,多个id 用,隔开", paramType = "path", required = true, dataType = "string")
    })
    @GetMapping(value = "/details/{id}")
    @ApiVersion(1)
    public RestfulVo findAllById(@PathVariable(value = "id") String id){
        return ResultUtil.success(attachmentUploadingRecordService.findAllById(SystemUtils.getIds(id)));
    }


}
