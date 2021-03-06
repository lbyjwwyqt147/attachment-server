package com.jwell.file.server.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/***
 * 文件名称: AttachmentUploadingRecord.java
 * 文件描述: 附件实体
 * 公 司: 积微物联
 * 内容摘要:
 * 其他说明:
 * 完成日期:2018年08月27日
 * 修改记录:
 * @version 1.0
 * @author ljy
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class AttachmentUploadingRecord {
    /** id */
    private Long id;

    /** 附件原始名称 */
    private String attachmentOriginalName;

    /** 附件上传后名称 */
    private String attachmentName;

    /** 附件所在目录 */
    private String fileDirectory;

    /** 附件绝对地址 */
    private String absoluteAddress;

    /** 附件访问地址 */
    private String callOnAddress;

    /** 附件后缀 */
    private String attachmentPostfix;

    /** 附件类型1：图片 2：文档 3：视频 4:zip 5:其他 */
    private Byte attachmentType;

    /** 附件大小(kb) */
    private String attachmentSize;

    /** 系统编码 例如：1001：设备系统 */
    private String systemCode;

    /** 业务编码 例如：1001001：访客管理 */
    private String businessCode;

    /** 业务类型 例如： 1001001001：访客头像 */
    private String businessType;

    /** 上传时间 */
    private Date createTime;

    /** 描述 */
    private String description;

    /** 扩展字段1 */
    private String attributeOne;

    /** 扩展字段2 */
    private String attributeTwo;

    /** 扩展字段3 */
    private String attributeThree;

    /** 扩展字段4 */
    private String attributeFour;
    /** 上传者名称 */
    private String uploaderName;
    /** 上传者ID */
    private String uploaderId;


    public AttachmentUploadingRecord() {

    }

    public AttachmentUploadingRecord(String attachmentOriginalName, String attachmentName, String fileDirectory, String absoluteAddress, String callOnAddress, String attachmentPostfix, Byte attachmentType, String attachmentSize, String systemCode, String businessCode, String businessType, String description, String uploaderName, String uploaderId) {
        this.attachmentOriginalName = attachmentOriginalName;
        this.attachmentName = attachmentName;
        this.fileDirectory = fileDirectory;
        this.absoluteAddress = absoluteAddress;
        this.callOnAddress = callOnAddress;
        this.attachmentPostfix = attachmentPostfix;
        this.attachmentType = attachmentType;
        this.attachmentSize = attachmentSize;
        this.systemCode = systemCode;
        this.businessCode = businessCode;
        this.businessType = businessType;
        this.description = description;
        this.uploaderId = uploaderId;
        this.uploaderName = uploaderName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAttachmentOriginalName() {
        return attachmentOriginalName;
    }

    public void setAttachmentOriginalName(String attachmentOriginalName) {
        this.attachmentOriginalName = attachmentOriginalName == null ? null : attachmentOriginalName.trim();
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName == null ? null : attachmentName.trim();
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory == null ? null : fileDirectory.trim();
    }

    public String getAbsoluteAddress() {
        return absoluteAddress;
    }

    public void setAbsoluteAddress(String absoluteAddress) {
        this.absoluteAddress = absoluteAddress == null ? null : absoluteAddress.trim();
    }

    public String getCallOnAddress() {
        return callOnAddress;
    }

    public void setCallOnAddress(String callOnAddress) {
        this.callOnAddress = callOnAddress == null ? null : callOnAddress.trim();
    }

    public String getAttachmentPostfix() {
        return attachmentPostfix;
    }

    public void setAttachmentPostfix(String attachmentPostfix) {
        this.attachmentPostfix = attachmentPostfix == null ? null : attachmentPostfix.trim();
    }

    public Byte getAttachmentType() {
        return attachmentType;
    }

    public void setAttachmentType(Byte attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentSize() {
        return attachmentSize;
    }

    public void setAttachmentSize(String attachmentSize) {
        this.attachmentSize = attachmentSize;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode == null ? null : systemCode.trim();
    }

    public String getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(String businessCode) {
        this.businessCode = businessCode == null ? null : businessCode.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getAttributeOne() {
        return attributeOne;
    }

    public void setAttributeOne(String attributeOne) {
        this.attributeOne = attributeOne == null ? null : attributeOne.trim();
    }

    public String getAttributeTwo() {
        return attributeTwo;
    }

    public void setAttributeTwo(String attributeTwo) {
        this.attributeTwo = attributeTwo == null ? null : attributeTwo.trim();
    }

    public String getAttributeThree() {
        return attributeThree;
    }

    public void setAttributeThree(String attributeThree) {
        this.attributeThree = attributeThree == null ? null : attributeThree.trim();
    }

    public String getAttributeFour() {
        return attributeFour;
    }

    public void setAttributeFour(String attributeFour) {
        this.attributeFour = attributeFour == null ? null : attributeFour.trim();
    }

    public String getUploaderName() {
        return uploaderName;
    }

    public void setUploaderName(String uploaderName) {
        this.uploaderName = uploaderName == null ? null : uploaderName.trim();
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId == null ? null : uploaderId.trim();
    }
}
