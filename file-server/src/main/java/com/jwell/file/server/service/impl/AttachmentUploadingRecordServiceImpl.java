package com.jwell.file.server.service.impl;

import com.jwell.file.server.entity.AttachmentUploadingRecord;
import com.jwell.file.server.repository.AttachmentUploadingRecordRepository;
import com.jwell.file.server.service.AttachmentUploadingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/***
 * 文件名称: AttachmentUploadingRecordServiceImpl.java
 * 文件描述: 附件 service 实现
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月27日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Slf4j
@Service
public class AttachmentUploadingRecordServiceImpl implements AttachmentUploadingRecordService {

    @Autowired
    private AttachmentUploadingRecordRepository attachmentUploadingRecordRepository;

    @Transactional
    @Override
    public Long insert(AttachmentUploadingRecord record) {
        Long fileId = 0L;
        try {
            AttachmentUploadingRecord save = attachmentUploadingRecordRepository.save(record);
            fileId = save.getId();
        } catch (Exception e) {
            log.error("上传文件数据入库出现异常.");
            e.printStackTrace();
        }
        return fileId;

    }

    @Transactional
    @Override
    public List<AttachmentUploadingRecord> batchInsert(List<AttachmentUploadingRecord> list) {
        List<AttachmentUploadingRecord> result = null;
        try {
            result = attachmentUploadingRecordRepository.saveAll(list);
        } catch (Exception e) {
            log.error("上传文件数据入库出现异常.");
            e.printStackTrace();
        }
        return result;
    }
}
