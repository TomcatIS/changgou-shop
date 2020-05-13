package com.dhcc.controller;

import com.dhcc.dto.CommonResult;
import com.dhcc.dto.FastDfsFile;
import com.dhcc.enu.ProcessStatusEnum;
import com.dhcc.exception.BaseException;
import com.dhcc.util.FastDfsUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @description 文件上传控制层
 * @author zhangqi
 * @date 2020/5/8
 */
@RestController
@RequestMapping("file")
@Validated
@Api(tags = "文件操作API接口")
public class FileController {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @description 文件上传
     */
    @PostMapping("/upload")
    @ApiOperation(value = "文件上传")
    @ApiImplicitParam(name = "file", value = "文件", dataTypeClass = MultipartFile.class, required = true)
    public CommonResult<Object> upload(@RequestBody MultipartFile file) {
        try {
            if (file == null) {
                throw new BaseException(ProcessStatusEnum.FILE_UPLOAD_IS_NULL.getCode(),
                        ProcessStatusEnum.FILE_UPLOAD_IS_NULL.getMessage());
            }
            byte[] bytes = file.getBytes();
            String filename = file.getOriginalFilename();
             if (StringUtils.isBlank(filename)) {
                 throw new BaseException(ProcessStatusEnum.FILE_UPLOAD_IS_NULL.getCode(),
                         ProcessStatusEnum.FILE_UPLOAD_IS_NULL.getMessage());
             }
            String ext = filename.substring(filename.lastIndexOf(".") + 1);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String uploadTime = simpleDateFormat.format(date);
            FastDfsFile fastDfsFile = new FastDfsFile(filename, bytes, ext, uploadTime);
            String[] uploadResults = FastDfsUtil.upload(fastDfsFile);
            // 获取文件存储路径
            String filePath = uploadResults[1];
            // 将文件路径和原文件名存入redis
            redisTemplate.opsForValue().set(filePath, filename);
            return CommonResult.success(uploadResults);
        } catch (IOException e) {
            throw new BaseException(ProcessStatusEnum.FILE_UPLOAD_ERROR.getCode(),
                    ProcessStatusEnum.FILE_UPLOAD_ERROR.getMessage());
        }
    }

    /**
     * @description 获取文件信息
     * @param groupName 组名 group1
     * @param fileName 包含路径的文件名 M00/00/00/wKi6ZF64GeCAL7O9AABJQmDBv2o456.png
     */
    @GetMapping("/get/fileInfo")
    @ApiOperation(value = "获取文件信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "组名", dataTypeClass = String.class),
            @ApiImplicitParam(name = "fileName", value = "文件名", dataTypeClass = String.class)
    })
    public CommonResult<Object> getFileInfo(@NotBlank(message = "文件所在组名不能为空") String groupName,
                                            @NotBlank(message = "文件名不能为空") String fileName) {
        String fileInfo = FastDfsUtil.getFileInfo(groupName, fileName);
        return CommonResult.success(fileInfo);
    }

    /**
     * @description 下载文件
     * @param groupName 组名 group1
     * @param fileName 包含路径的文件命名 M00/00/00/wKi6ZF64GeCAL7O9AABJQmDBv2o456.png
     */
    @GetMapping("/download")
    @ApiOperation(value = "下载文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "组名", dataTypeClass = String.class),
            @ApiImplicitParam(name = "fileName", value = "文件名", dataTypeClass = String.class)
    })
    public String download(@NotBlank(message = "文件所在组名不能为空") String groupName,
                                         @NotBlank(message = "文件名不能为空") String fileName, HttpServletResponse response) {
        // 从redis获取原文件名
        String originalName = (String)redisTemplate.opsForValue().get(fileName);
        if (StringUtils.isEmpty(originalName)) {
            originalName = UUID.randomUUID().toString();
        }
        byte[] bytes = new byte[1024];
        int len;
        try (InputStream inputStream = FastDfsUtil.download(groupName, fileName);
             BufferedInputStream bis = new BufferedInputStream(inputStream);
             OutputStream outputStream = response.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(outputStream)) {
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(originalName, "UTF-8"));
            while((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
            bos.flush();
        } catch (IOException e) {
            throw new BaseException(ProcessStatusEnum.FILE_DOWNLOAD_ERROR.getCode(),
                    ProcessStatusEnum.FILE_DOWNLOAD_ERROR.getMessage());
        }
        return "文件下载成功";
    }

    /**
     * @description 删除文件
     * @param groupName 组名 group1
     * @param fileName 包含路径的文件命名 M00/00/00/wKi6ZF64GeCAL7O9AABJQmDBv2o456.png
     */
    @DeleteMapping("/delete")
    @ApiOperation(value = "删除文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupName", value = "组名", dataTypeClass = String.class),
            @ApiImplicitParam(name = "fileName", value = "文件名", dataTypeClass = String.class)
    })
    public CommonResult<Object> delete(@NotBlank(message = "文件所在组名不能为空") String groupName,
                                       @NotBlank(message = "文件名不能为空") String fileName) {
        try {
            redisTemplate.delete(fileName);
        } catch (Exception e) {
            logger.error("删除文件的redis缓存失败");
            throw new BaseException(ProcessStatusEnum.FILE_DELETE_ERROR.getCode(),
                    ProcessStatusEnum.FILE_DELETE_ERROR.getMessage());
        }
        FastDfsUtil.delete(groupName, fileName);
        return CommonResult.success("文件删除成功");
    }
}
