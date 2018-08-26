package com.jwell.file.server.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/***
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FileDataVo implements Serializable {

    //文件id
    private Long id;
    // 文件原始名称
    private String fileName;
    // 文件上传后名称
    private String newFileName;
    // 文件请求url
    private String requestUrl;

    public FileDataVo(Long id, String fileName, String newFileName, String requestUrl) {
        this.id = id;
        this.fileName = fileName;
        this.newFileName = newFileName;
        this.requestUrl = requestUrl;
    }
}
