package com.jwell.file.server.service.impl;

import com.jwell.file.common.exception.ErrorCodeEnum;
import com.jwell.file.common.file.FileEnum;
import com.jwell.file.common.file.FileUtil;
import com.jwell.file.common.restful.RestfulVo;
import com.jwell.file.common.restful.ResultUtil;
import com.jwell.file.common.util.ThreadPoolExecutorFactory;
import com.jwell.file.server.domain.dto.FileDataDto;
import com.jwell.file.server.domain.vo.FileDataVo;
import com.jwell.file.server.entity.AttachmentUploadingRecord;
import com.jwell.file.server.service.AttachmentUploadingRecordService;
import com.jwell.file.server.service.FlieUploadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadPoolExecutor;

/***
 * 文件名称: FlieUploadingServiceImpl.java
 * 文件描述: 文件上传 service  实现
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月27日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Service
public class FlieUploadingServiceImpl implements FlieUploadingService {
    private ThreadPoolExecutor threadPoolExecutor = ThreadPoolExecutorFactory.getThreadPoolExecutor();
    @Autowired
    private AttachmentUploadingRecordService attachmentUploadingRecordService;
    //文件保存路径
    @Value("${data.file.dir}")
    private String filePath;
    // 系统访问url
    @Value("${data.hostName}")
    private String hostName;


    @Override
    public RestfulVo startUploading(List<MultipartFile> files, FileDataDto data) {
        //判断文件是否为空
        if (files.isEmpty()) {
            return ResultUtil.resultInfo(ErrorCodeEnum.FILEISNULL, null);
        }
        List<AttachmentUploadingRecord> recordList = new CopyOnWriteArrayList<>();
        for (MultipartFile file : files) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            //得到文件后缀名
            String suffixName = FileUtil.getSuffixName(fileName).toLowerCase();
            //重命名文件名(避免文件名重复)
            String newFileName = FileUtil.getNewFileName(suffixName);
            //得到文件所属分类(文档、图片、视频,ZIP)
            FileEnum fileEnum = FileUtil.getFileTypeEnum(suffixName);
            //文件目录
            String path  = getPath(fileEnum.getName(), newFileName);
            //创建文件路径
            File dataFile = new File(path);
            //判断文件父目录是否存在
            if (!dataFile.getParentFile().exists()) {
                dataFile.getParentFile().mkdirs();
            }
            //判断文件是否已经存在
            /*if (dataFile.exists()) {
                return ResultUtil.error(ErrorCodeEnum.ERROR.getCode(), "上传文件已经存在.");
            }*/

            try {
                //上传文件到本地磁盘
                file.transferTo(dataFile);
                // 请求访问文件的 url 地址 url="http://域名/项目名/images/"+newFileName
                String requestUrl = this.getFileRequestUrl(fileEnum.getName(), newFileName);
                // 数据入库
                AttachmentUploadingRecord record = new AttachmentUploadingRecord(fileName, newFileName, filePath+ "/" + fileEnum.getName(), path, requestUrl, suffixName, fileEnum.getCode(), FileUtil.getFileSize(dataFile), data.getSystemCode(), data.getBusinessCode(), data.getBusinessType(), data.getDescription());
                recordList.add(record);
            } catch (IOException e) {
                e.printStackTrace();
                return ResultUtil.error(ErrorCodeEnum.ERROR.getCode(), "上传文件出现错误.");
            }
        }
        return  this.disposeFileData(recordList);
    }


    /**
     * 文件路径
     * @param fileType 文件类型
     * @param fileName  文件名
     * @return
     */
    private String getPath(String fileType, String fileName){
        StringBuffer path = new StringBuffer(filePath);
        path.append("/").append(fileType);
        path.append("/").append(fileName);
        return FileUtil.convertFilePath(path.toString());
    }

    /**
     * 得到文件请求的url 地址
     * @param fileType  文件类型
     * @param fileName  文件名称
     * @return
     */
    private String getFileRequestUrl(String fileType, String fileName){
        StringBuffer requestUrl = new StringBuffer(hostName);
        requestUrl.append(fileType).append("/").append(fileName);
        return requestUrl.toString();
    }

    /**
     *  处理上传文件数据
     * @param recordList
     * @return
     */
    private RestfulVo disposeFileData(List<AttachmentUploadingRecord> recordList) {
        List<AttachmentUploadingRecord> list = attachmentUploadingRecordService.batchInsert(recordList);
        List<FileDataVo> dataList =  new CopyOnWriteArrayList<>();
        if (list != null && !list.isEmpty()) {
            for (AttachmentUploadingRecord record : list) {
                dataList.add(new FileDataVo(record.getId(), record.getAttachmentOriginalName(), record.getAttachmentName(), record.getCallOnAddress()));
            }
            return ResultUtil.success(dataList);
        }
        // 如果入库失败就删除上传文件
        for (AttachmentUploadingRecord record : recordList) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    FileUtil.delete(record.getAbsoluteAddress());
                }
            });
        }
        return ResultUtil.fail();
    }

}
