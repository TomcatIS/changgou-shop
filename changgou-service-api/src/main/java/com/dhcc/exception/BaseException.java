package com.dhcc.exception;

/**
 * @description 自定义运行时异常
 * @author zhangqi
 * @date 2020/5/9
 */
public class BaseException extends RuntimeException {
    private int code;
    private String message;

    public BaseException() {
        super();
    }

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
