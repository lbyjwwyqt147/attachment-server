package com.jwell.file.server.service.impl;

import com.jwell.file.common.file.FileUtil;
import com.jwell.file.common.restful.RestfulVo;
import com.jwell.file.common.restful.ResultUtil;
import com.jwell.file.server.entity.AttachmentUploadingRecord;
import com.jwell.file.server.repository.AttachmentUploadingRecordRepository;
import com.jwell.file.server.service.FlieDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/***
 * 文件名称: FlieDownloadServiceImpl.java
 * 文件描述: 文件下载 service  实现
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月28日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Service
public class FlieDownloadServiceImpl implements FlieDownloadService {
    @Autowired
    private AttachmentUploadingRecordRepository attachmentUploadingRecordRepository;

    @Override
    public RestfulVo downloadFile(Long id, HttpServletResponse response) {
        Optional<AttachmentUploadingRecord> records = attachmentUploadingRecordRepository.findById(id);
        if (records != null) {
            AttachmentUploadingRecord record = records.get();
            FileUtil.downloadFile(record.getAbsoluteAddress(), record.getAttachmentOriginalName(), response);
        }
        return ResultUtil.success();
    }

    @Override
    public RestfulVo downloadZipFile(HttpServletResponse response) {
        return null;
    }
}
