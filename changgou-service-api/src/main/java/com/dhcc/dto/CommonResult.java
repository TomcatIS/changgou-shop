package com.dhcc.dto;

import com.dhcc.enu.ProcessStatusEnum;

import java.io.Serializable;

/**
 * @author zhangqi
 * @date 2020/5/8
 */
public class CommonResult<T> implements Serializable {

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;

    public static <T> CommonResult<T> error(CommonResult<?> result) {
        return error(result.getCode(), result.getMessage());
    }

    public static <T> CommonResult<T> error(Integer code, String message) {
        CommonResult<T> result = new CommonResult<>();
        result.code = code;
        result.message = message;
        return result;
    }

    public static <T> CommonResult<T> success() {
        CommonResult<T> result = new CommonResult<>();
        result.code = ProcessStatusEnum.SUCCESS.getCode();
        result.message = ProcessStatusEnum.SUCCESS.getMessage();
        return result;
    }

    public static <T> CommonResult<T> success(T data) {
        CommonResult<T> result = new CommonResult<>();
        result.code = ProcessStatusEnum.SUCCESS.getCode();
        result.data = data;
        result.message = ProcessStatusEnum.SUCCESS.getMessage();
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
