package com.jwell.file.server.service;

import com.jwell.file.common.restful.RestfulVo;
import com.jwell.file.server.domain.dto.FileDataDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/***
 * 文件名称: FlieUploadingService.java
 * 文件描述: 文件上传 service
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月27日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public interface FlieUploadingService {

    /**
     * 文件上传
     * @param files 文件
     * @param data
     * @return
     */
    RestfulVo startUploading(List<MultipartFile> files, FileDataDto data);

}
