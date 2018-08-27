package com.jwell.file.server.service;

import com.jwell.file.server.entity.AttachmentUploadingRecord;

import java.util.List;

/***
 * 文件名称: AttachmentUploadingRecordService.java
 * 文件描述: 附件 service
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月27日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public interface AttachmentUploadingRecordService {

    /**
     * 新增纪录
     * @param record
     * @return
     */
    Long insert(AttachmentUploadingRecord record);


    /**
     * 批量新增纪录
     * @param list
     * @return
     */
    List<AttachmentUploadingRecord> batchInsert(List<AttachmentUploadingRecord> list);
}
