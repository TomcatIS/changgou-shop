package com.dhcc.util;

import com.dhcc.dto.FastDfsFile;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @description fastdfs 工具类
 * @author zhangqi
 * @date 2020/5/8
 */
public class FastDfsUtil {
    private static final Logger logger = LoggerFactory.getLogger(FastDfsUtil.class);

    /**
     * 加载tracker连接信息
     */
    static {
        try {
            // 获取配置文件
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            // 加载配置文件
            logger.info("----------加载fastdfs配置文件----------");
            ClientGlobal.init(filePath);
        } catch (Exception e) {

            throw new BaseException("---------fastdfs client 初始化失败----------");
        }
    }

    /**
     * @description 文件上传
     */
    public static String[] upload(FastDfsFile file) {
        // 附加参数
        NameValuePair[] metaList = new NameValuePair[3];
        metaList[0] = new NameValuePair("author", file.getAuthor());
        metaList[1] = new NameValuePair("upload_time", file.getTime());
        metaList[2] = new NameValuePair("md5", file.getMd5());
        // 接受返回数据
        String[] uploadResults;
        StorageClient storageClient = getStorageClient();
        try {
            // 第一个参数：文件字节数组；第二个参数：文件扩展名；第三个参数：附加参数
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), metaList);
        } catch (Exception e) {
            throw new BaseException("文件上传失败，失败文件名为：" + file.getFileName());
        }
        if (uploadResults == null) {
            throw new BaseException("文件上传返回值为空，上传失败，error code：" + storageClient.getErrorCode());
        }
        return uploadResults;
    }


    /**
     * @description 获取文件信息
     * @param groupName 组名 group1
     * @param fileName 包含路径的文件命名 M00/00/00/wKi6ZF64GeCAL7O9AABJQmDBv2o456.png
     */
    public static String getFileInfo(String groupName, String fileName) {
        StorageClient storageClient = getStorageClient();
        try {
            FileInfo fileInfo = storageClient.get_file_info(groupName, fileName);
            String sourceIpAddr = fileInfo.getSourceIpAddr();
            long fileSize = fileInfo.getFileSize();
            Date createTimestamp = fileInfo.getCreateTimestamp();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = simpleDateFormat.format(createTimestamp);
            return "源IP地址：" + sourceIpAddr + ";" +
                    "文件大小：" + fileSize + ";" +
                    "上传时间：" + date;
        } catch (Exception e) {
            throw new BaseException(ProcessStatusEnum.FILE_GET_INFO_ERROR.getCode(),
                    ProcessStatusEnum.FILE_GET_INFO_ERROR.getMessage());
        }
    }

    /**
     * @description 下载文件
     * @param groupName 组名 group1
     * @param fileName 包含路径的文件命名 M00/00/00/wKi6ZF64GeCAL7O9AABJQmDBv2o456.png
     */
    public static InputStream download(String groupName, String fileName) {
        StorageClient storageClient = getStorageClient();
        try {
            byte[] bytes = storageClient.download_file(groupName, fileName);
            return new ByteArrayInputStream(bytes);
        } catch (Exception e) {
            throw new BaseException(ProcessStatusEnum.FILE_DOWNLOAD_ERROR.getCode(),
                    ProcessStatusEnum.FILE_DOWNLOAD_ERROR.getMessage());
        }
    }

    /**
     * @description 删除文件
     * @param groupName 组名 group1
     * @param fileName 包含路径的文件命名 M00/00/00/wKi6ZF64GeCAL7O9AABJQmDBv2o456.png
     */
    public static void delete(String groupName, String fileName) {
        StorageClient storageClient = getStorageClient();
        try {
            storageClient.delete_file(groupName, fileName);
        } catch (Exception e) {
            throw new BaseException(ProcessStatusEnum.FILE_DELETE_ERROR.getCode(),
                    ProcessStatusEnum.FILE_DELETE_ERROR.getMessage());
        }
    }

    private static StorageClient getStorageClient() {
        TrackerServer trackerServer = getTrackerServer();
        // 通过trackerServer获取的storageClient，从而访问storage实现文件上传
        return new StorageClient(trackerServer, null);
    }

    private static TrackerServer getTrackerServer() {
        // 通过trackerClient客户端访问trackerServer
        TrackerClient trackerClient = new TrackerClient();
        try {
            return trackerClient.getConnection();
        } catch (IOException e) {
            throw new BaseException(ProcessStatusEnum.FDFS_TRACKER_SERVER_CONNECT_FAIL.getCode(),
                    ProcessStatusEnum.FDFS_TRACKER_SERVER_CONNECT_FAIL.getMessage());
        }
    }
}
