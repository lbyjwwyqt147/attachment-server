package com.jwell.file.common.restful;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jwell.file.common.exception.ErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class RestfulVo  implements Serializable {

    private static final long serialVersionUID = -4450312255234324795L;

    private Integer status;  //状态码
    private String message; // 描述信息
    private Object data;   // 数据
    private Object extend; //扩展数据
    private Long totalElements; //分页数据的　总记录条数
    private Boolean success = true;
    @JSONField(serialize=false)
    @JsonIgnore
    private ErrorCodeEnum errorCodeEnum;

    public RestfulVo(){

    }

    public RestfulVo(ErrorCodeEnum  errorCodeEnum){
        this.status = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
        if (this.status != 0){
            this.success = false;
        }
    }

    public RestfulVo(ErrorCodeEnum  errorCodeEnum,Object data,Object extend){
        this.status = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
        this.data = data;
        this.extend = extend;
        if (this.status != 0){
            this.success = false;
        }
    }

    public RestfulVo(ErrorCodeEnum  errorCodeEnum,Object data){
        this.status = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
        this.data = data;
        if (this.status != 0){
            this.success = false;
        }
    }

    /**
     * 获取实例
     * @return
     */
    public static RestfulVo getInstance(){
        return new RestfulVo();
    }
}
