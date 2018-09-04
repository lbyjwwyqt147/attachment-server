package com.jwell.file.server.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/***
 *
 */
@ApiModel
@Data
@EqualsAndHashCode(callSuper = false)
public class FileDataDto implements Serializable {

    /** 系统编码 例如：1001：设备系统 */
    @ApiModelProperty(value = "系统编码")
    private String systemCode;
    /** 业务编码 例如：1001001：访客管理 */
    @ApiModelProperty(value = "业务编码")
    private String businessCode;
    /** 业务类型 例如： 1001001001：访客头像 */
    @ApiModelProperty(value = "业务类型")
    private String businessType;
    /** 描述 */
    @ApiModelProperty(value = "描述")
    private String description;
    /** 上传者 */
    @ApiModelProperty(value = "上传者姓名")
    private String uploaderName;
    @ApiModelProperty(value = "上传者ID")
    private String uploaderId;
}
