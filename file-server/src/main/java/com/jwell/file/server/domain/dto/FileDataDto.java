package com.jwell.file.server.domain.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/***
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FileDataDto implements Serializable {

    /** 系统编码 例如：1001：设备系统 */
    private String systemCode;

    /** 业务编码 例如：1001001：访客管理 */
    private String businessCode;

    /** 业务类型 例如： 1001001001：访客头像 */
    private String businessType;
    /** 描述 */
    private String description;
}
