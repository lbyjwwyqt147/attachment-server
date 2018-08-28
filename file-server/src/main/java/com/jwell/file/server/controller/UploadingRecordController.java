package com.jwell.file.server.controller;

import com.jwell.file.common.restful.RestfulVo;
import com.jwell.file.common.restful.ResultUtil;
import com.jwell.file.common.util.SystemUtils;
import com.jwell.file.server.annotation.ApiVersion;
import com.jwell.file.server.service.AttachmentUploadingRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
@RestController
public class UploadingRecordController extends BaseController {
    @Autowired
    private AttachmentUploadingRecordService attachmentUploadingRecordService;

    /**
     * 单条删除数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
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
    @RequestMapping(value = "/batchDelete/{id}")
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
    @GetMapping(value = "/details/{id}")
    @ApiVersion(1)
    public RestfulVo findAllById(@PathVariable(value = "id") String id){
        return ResultUtil.success(attachmentUploadingRecordService.findAllById(SystemUtils.getIds(id)));
    }


}
