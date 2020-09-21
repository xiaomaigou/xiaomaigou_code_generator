package com.xiaomaigou.code.utils;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Zip压缩文件工具类
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/9/22 0:17
 */
public class ZipUtils {

    private static final Logger logger = LoggerFactory.getLogger(ZipUtils.class);

    /**
     * 解压指定的ZIP压缩文件到当前目录(并删除压缩文件)
     *
     * @param file 指定的ZIP压缩文件
     * @return 执行结果
     */
    public static boolean unZip(String file) {
        return unZip(new File(file));
    }

    /**
     * 解压指定的ZIP压缩文件到当前目录(并删除压缩文件)
     *
     * @param file 指定的ZIP压缩文件
     * @return 执行结果
     */
    public static boolean unZip(File file) {
        // 文件基本名称(不包含文件后缀)
        String baseName = FilenameUtils.getBaseName(file.getName());
        String dest = file.getParent() + File.separator + baseName;
        return unZip(file, dest);
    }

    /**
     * 解压指定的ZIP压缩文件到指定目录(并删除压缩文件)
     *
     * @param file 指定的ZIP压缩文件
     * @param dest 指定目录
     * @return 执行结果
     */
    public static boolean unZip(String file, String dest) {
        return unZip(new File(file), dest);
    }

    /**
     * 解压指定的ZIP压缩文件到指定目录(并删除压缩文件)
     *
     * @param file 指定的ZIP压缩文件
     * @param dest 指定目录
     * @return 执行结果
     */
    public static boolean unZip(File file, String dest) {
        return unZip(file, dest, null, true);
    }

    /**
     * 根据指定的密码解压指定的ZIP压缩文件到当前目录
     *
     * @param file          指定的ZIP压缩文件
     * @param password      指定的密码
     * @param deleteZipFile 是否删除压缩文件
     * @return 执行结果
     */
    public static boolean unZip(String file, String password, boolean deleteZipFile) {
        return unZip(new File(file), password, deleteZipFile);
    }

    /**
     * 根据指定的密码解压指定的ZIP压缩文件到当前目录
     *
     * @param file          指定的ZIP压缩文件
     * @param password      指定的密码
     * @param deleteZipFile 是否删除压缩文件
     * @return 执行结果
     */
    public static boolean unZip(File file, String password, boolean deleteZipFile) {
        // 文件基本名称(不包含文件后缀)
        String baseName = FilenameUtils.getBaseName(file.getName());
        String dest = file.getParent() + File.separator + baseName;
        return unZip(file, dest, password, deleteZipFile);
    }

    /**
     * 根据指定的密码解压指定的ZIP压缩文件到指定目录
     *
     * @param file          指定的ZIP压缩文件
     * @param dest          指定目录
     * @param password      指定的密码
     * @param deleteZipFile 是否删除压缩文件
     * @return 执行结果
     */
    public static boolean unZip(String file, String dest, String password, boolean deleteZipFile) {
        return unZip(new File(file), dest, password, deleteZipFile);
    }

    /**
     * 根据指定的密码解压指定的ZIP压缩文件到指定目录
     *
     * @param file          指定的ZIP压缩文件
     * @param dest          指定目录
     * @param password      指定的密码
     * @param deleteZipFile 是否删除压缩文件
     * @return 执行结果
     */
    public static boolean unZip(File file, String dest, String password, boolean deleteZipFile) {
        ZipFile zipFile = new ZipFile(file);
        /*
         * zip格式在不同平台上使用不同软件压缩，结果大致为两类:
         * 1.Windows下使用WinRAR、好压、快压、百度压缩等工具压缩的文件文件名为GBK编码;
         * 2.Linux、MacOS等下压缩的zip文件文件名为UTF-8编码;
         */
//        zipFile.setCharset(Charset.forName("UTF-8"));
        zipFile.setCharset(Charset.forName("GBK"));
        try {
            if (!zipFile.isValidZipFile()) {
                throw new ZipException("压缩文件不合法或已损坏!");
            }
            if (zipFile.isEncrypted() && StringUtils.isNotEmpty(password)) {
                zipFile.setPassword(password.toCharArray());
            }
            // 如果该文件存在则先删除
            FileUtils.deleteQuietly(new File(dest));
            zipFile.extractAll(dest);
            // 删除压缩文件
            if (deleteZipFile) {
                FileUtils.deleteQuietly(file);
            }
        } catch (ZipException e) {
            logger.error(String.format("解压文件失败,AbsolutePath=[%s],解压目录dest=[%s]", file.getAbsolutePath(), dest), e);
            return Boolean.FALSE;
        } finally {
            // 删除压缩文件
//            if (deleteZipFile) {
//                FileUtils.deleteQuietly(file);
//            }
        }
        return Boolean.TRUE;
    }

    /**
     * 压缩指定文件或文件夹到当前目录
     *
     * @param src 需要压缩的文件或文件夹
     * @return 压缩成功返回压缩文件保存位置的绝对路径，压缩失败返回null
     */
    public static String zip(String src) {
        return zip(src, null, null, null);
    }

    /**
     * 压缩指定文件或文件夹到指定位置
     *
     * @param src  需要压缩的文件或文件夹
     * @param dest 压缩文件保存位置(默认为当前目录)
     * @return 压缩成功返回压缩文件保存位置的绝对路径，压缩失败返回null
     */
    public static String zip(String src, String dest) {
        return zip(src, dest, null, null);
    }

    /**
     * 根据指定的密码压缩指定文件或文件夹到指定位置
     *
     * @param src      需要压缩的文件或文件夹
     * @param dest     压缩文件保存位置(默认为当前目录)
     * @param password 密码
     * @return 压缩成功返回压缩文件保存位置的绝对路径，压缩失败返回null
     */
    public static String zip(String src, String dest, String password) {
        return zip(src, dest, password, null);
    }

    /**
     * 根据指定的密码压缩指定文件或文件夹到指定位置
     *
     * @param src         需要压缩的文件或文件夹
     * @param dest        压缩文件保存位置(默认为当前目录)
     * @param password    密码
     * @param charsetName 编码方式(默认GBK)
     * @return 压缩成功返回压缩文件保存位置的绝对路径，压缩失败返回null
     */
    public static String zip(String src, String dest, String password, String charsetName) {
        if (StringUtils.isEmpty(src) || !new File(src).exists()) {
            return null;
        }
        File srcFile = new File(src);
        // 压缩文件保存位置(默认为当前目录)
        if (StringUtils.isEmpty(dest)) {
            dest = srcFile.getParent() + File.separator + FilenameUtils.getBaseName(srcFile.getName()) + ".zip";
        }
        ZipFile zipFile = new ZipFile(dest);
        // 默认编码方式为GBK
//        zipFile.setCharset(Charset.forName("UTF-8"));
        zipFile.setCharset(Charset.forName("GBK"));
        if (StringUtils.isNotEmpty(charsetName)) {
            zipFile.setCharset(Charset.forName(charsetName));
        }
        // 设置压缩参数
        ZipParameters zipParameters = new ZipParameters();
        // 压缩方式
        zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
        // 压缩等级
        zipParameters.setCompressionLevel(CompressionLevel.NORMAL);
        // 设置密码
        if (StringUtils.isNotEmpty(password)) {
            zipParameters.setEncryptFiles(true);
            zipParameters.setEncryptionMethod(EncryptionMethod.AES);
            // Below line is optional. AES 256 is used by default. You can override it to use AES 128. AES 192 is supported only for extracting.
            zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);
            zipFile.setPassword(password.toCharArray());
        }
        try {
            // 压缩
            if (srcFile.isDirectory()) {
                zipFile.addFolder(srcFile, zipParameters);
            } else {
                zipFile.addFile(srcFile, zipParameters);
            }
            return dest;
        } catch (ZipException e) {
            logger.error(String.format("压缩文件失败,src=[%s],dest=[%s]，password=[%s],charsetName=[%s]", src, dest, password, charsetName), e);
        }
        return null;
    }
}
