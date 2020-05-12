package com.dhcc.dto;

/**
 * 文件信息封装类
 * @author zhangqi
 * @date 2020/5/8
 */
public class FastDfsFile {
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件内容
     */
    private byte[] content;
    /**
     * 作者
     */
    private String author;
    /**
     * md5值
     */
    private String md5;
    /**
     * 扩展名
     */
    private String ext;
    /**
     * 上传时间
     */
    private String time;

    public FastDfsFile(){
        super();
    }

    public FastDfsFile(String fileName, byte[] content, String author, String md5, String ext, String time) {
        this.fileName = fileName;
        this.content = content;
        this.author = author;
        this.md5 = md5;
        this.ext = ext;
        this.time = time;
    }

    public FastDfsFile(String fileName, byte[] content, String ext) {
        this.fileName = fileName;
        this.content = content;
        this.ext = ext;
    }

    public FastDfsFile(String fileName, byte[] content, String ext, String time) {
        this.fileName = fileName;
        this.content = content;
        this.ext = ext;
        this.time = time;
    }

    public FastDfsFile(String fileName, byte[] content, String md5, String ext, String time) {
        this.fileName = fileName;
        this.content = content;
        this.md5 = md5;
        this.ext = ext;
        this.time = time;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
