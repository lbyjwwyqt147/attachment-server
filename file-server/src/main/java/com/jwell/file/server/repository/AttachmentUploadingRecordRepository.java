package com.jwell.file.server.repository;

import com.jwell.file.server.entity.AttachmentUploadingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/***
 * 文件名称: AttachmentUploadingRecordRepository.java
 * 文件描述: 附件  Repository
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月27日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
public interface AttachmentUploadingRecordRepository extends JpaRepository<AttachmentUploadingRecord, Long>, JpaSpecificationExecutor<AttachmentUploadingRecord> {

}
