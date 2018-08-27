package com.jwell.file.common.exception;

/***
 * 异常代码 值
 */

public enum ErrorCodeEnum {

    SUCCESS("ok.", 0),
    FAIL("fail.", 1),
    ERROR("系统运行中发生错误.",500),
    FILEISNULL("上传文件为空.",501);

    private String message ;
    private Integer code ;

    ErrorCodeEnum(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
