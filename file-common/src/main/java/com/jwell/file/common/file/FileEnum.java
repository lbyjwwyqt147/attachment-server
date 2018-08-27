package com.jwell.file.common.file;

import lombok.Data;

/***
 *
 * @FileName: FileEnum
 * @Company:
 * @author    ljy
 * @Date      2018年08月24日
 * @version   1.0.0
 * @remark:   上传文件分类 ENUM
 * @explain
 *
 *
 */
public enum FileEnum {

    IMAGES((byte)1, "images"), DOCUMENTS((byte)2, "documents"), VIDEOS((byte)3, "videos"), ZIPS((byte)4, "zips"), OTHERS((byte)5, "others");


    private byte code;
    private String name;

    FileEnum(byte code, String name) {
        this.name = name;
        this.code = code;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
