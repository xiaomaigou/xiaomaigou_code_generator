package com.xiaomaigou.code.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传工具类
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/23 16:38
 */
public class FileUtil {

    private static final Logger logger = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 上传文件
     *
     * @param savePath      保存路径
     * @param multipartFile 文件
     * @return File
     */
    public static File uploadFile(String savePath, MultipartFile multipartFile) {
        // 文件保存路径
        String path = savePath + File.separator + multipartFile.getOriginalFilename();
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            logger.debug(String.format("文件上传成功，文件名=[%s],文件路径=[%s]", multipartFile.getOriginalFilename(), file.getPath()));
            return file;
        } catch (IOException e) {
            logger.error("文件上传失败!", e);
            return null;
        }
    }

    /**
     * 上传文件
     *
     * @param savePath      保存路径
     * @param multipartFile 文件
     * @param fileName      文件名
     * @return File
     */
    public static File uploadFile(String savePath, MultipartFile multipartFile, String fileName) {
        // 文件保存路径
        String path = savePath + File.separator + fileName;
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(file);
            logger.debug(String.format("文件上传成功，文件名=[%s],文件路径=[%s]", file.getName(), file.getPath()));
            return file;
        } catch (IOException e) {
            logger.error("文件上传失败!", e);
            return null;
        }
    }
}
