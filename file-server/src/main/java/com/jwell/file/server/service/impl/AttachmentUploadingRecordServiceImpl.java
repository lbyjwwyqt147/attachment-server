package com.jwell.file.server.service.impl;

import com.jwell.file.common.file.FileUtil;
import com.jwell.file.common.util.ThreadPoolExecutorFactory;
import com.jwell.file.server.entity.AttachmentUploadingRecord;
import com.jwell.file.server.repository.AttachmentUploadingRecordRepository;
import com.jwell.file.server.service.AttachmentUploadingRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

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
    private ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();

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

    @Transactional
    @Override
    public int deleteByIdIn(List<Long> ids) {
        List<AttachmentUploadingRecord> records = this.findAllById(ids);
        int result = attachmentUploadingRecordRepository.deleteByIdIn(ids);
        if (result > 0) {
            this.deleteFile(records);
        }
        return result;
    }

    @Transactional
    @Override
    public int deleteById(Long id) {
        List<Long> ids = new LinkedList<>();
        ids.add(id);
        List<AttachmentUploadingRecord> records = this.findAllById(ids);
        int result =  attachmentUploadingRecordRepository.deleteAllById(id);
        if (result > 0) {
            this.deleteFile(records);
        }
        return result;
    }

    @Override
    public List<AttachmentUploadingRecord> findAllById(List<Long> ids) {
       // Criteria<AttachmentUploadingRecord> criteria = new Criteria<>();
        // criteria.add(Restrictions.in("id", ids));
        return attachmentUploadingRecordRepository.findAllById(ids);
    }

    /**
     * 删除文件
     * @param records 记录
     */
    private void deleteFile(List<AttachmentUploadingRecord> records) {
        if (records != null && !records.isEmpty()) {
            for (AttachmentUploadingRecord record : records) {
                threadPoolExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        FileUtil.delete(record.getAbsoluteAddress());
                    }
                });
            }
        }
    }
}
