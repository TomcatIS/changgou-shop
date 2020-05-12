package com.dhcc.enu;

/**
 * @author zhangqi
 * @date 2020/5/7
 */
public enum ProcessStatusEnum {
    /**
     * 成功
     */
    SUCCESS(200, "成功"),

    /**
     * 请求参数校验失败
     */
    REQUEST_PARAM_INVALID(1001, "请求参数不合法!"),

    FILE_UPLOAD_ERROR(2001, "文件上传失败!"),

    FILE_UPLOAD_IS_NULL(2002, "上传文件为空!"),

    FILE_GET_INFO_ERROR(2003, "获取文件信息失败!"),

    FILE_DOWNLOAD_ERROR(2004, "文件下载失败!"),

    FILE_DELETE_ERROR(2005, "文件删除失败!"),

    FDFS_TRACKER_SERVER_CONNECT_FAIL(3001, "连接trackerServer失败!"),


    RUNTIME_ERROR(5000, "运行时异常!"),

    OTHER_ERROR(10000, "其他错误!");

    private int code;
    private String message;

    ProcessStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
