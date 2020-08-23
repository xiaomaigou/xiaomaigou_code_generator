package com.xiaomaigou.code.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.Serializable;

/**
 * 公共属性
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 12:46
 */
@ApiModel(description = "公共属性")
public class Common implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 包名
     */
    @ApiModelProperty(value = "包名", name = "packageName")
    private String packageName;

    /**
     * 模块名称
     */
    @ApiModelProperty(value = "模块名称", name = "moduleName")
    private String moduleName;

    /**
     * 作者
     */
    @ApiModelProperty(value = "作者", name = "author")
    private String author;

    /**
     * 版本
     */
    @ApiModelProperty(value = "版本", name = "version")
    private String version;

    /**
     * 包名(文件目录格式)
     */
    @ApiModelProperty(value = "包名(文件目录格式,该字段通过包名转化而来)", name = "packageNameDir")
    private String packageNameDir;

    /**
     * 模块名称(文件目录格式)
     */
    @ApiModelProperty(value = "模块名称(文件目录格式,该字段通过模块名称转化而来)", name = "moduleNameDir")
    private String moduleNameDir;

    /**
     * 时间
     */
    @ApiModelProperty(value = "时间", name = "dateTime")
    private String dateTime;

    /**
     * 文件夹目录分隔符
     */
    @ApiModelProperty(value = "文件夹目录分隔符", name = "fileSeparator")
    private String fileSeparator;

    public Common() {
    }

    public Common(String packageName, String moduleName, String author, String version, String packageNameDir, String moduleNameDir, String dateTime, String fileSeparator) {
        this.packageName = packageName;
        this.moduleName = moduleName;
        this.author = author;
        this.version = version;
        this.packageNameDir = packageNameDir;
        this.moduleNameDir = moduleNameDir;
        this.dateTime = dateTime;
        this.fileSeparator = fileSeparator;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPackageNameDir() {
        this.packageNameDir = StringUtils.replace(this.getPackageName(), ".", File.separator);
        return packageNameDir;
    }

    public void setPackageNameDir(String packageNameDir) {
        this.packageNameDir = packageNameDir;
    }

    public String getModuleNameDir() {
        this.moduleNameDir = StringUtils.replace(this.getModuleName(), ".", File.separator);
        return moduleNameDir;
    }

    public void setModuleNameDir(String moduleNameDir) {
        this.moduleNameDir = moduleNameDir;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getFileSeparator() {
        return fileSeparator;
    }

    public void setFileSeparator(String fileSeparator) {
        this.fileSeparator = fileSeparator;
    }

    @Override
    public String toString() {
        return "Common{" +
                "packageName='" + packageName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", author='" + author + '\'' +
                ", version='" + version + '\'' +
                ", packageNameDir='" + packageNameDir + '\'' +
                ", moduleNameDir='" + moduleNameDir + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", fileSeparator='" + fileSeparator + '\'' +
                '}';
    }
}
