package com.xiaomaigou.code.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
     * 时间
     */
    @ApiModelProperty(value = "时间", name = "dateTime")
    private String dateTime;

    public Common() {
    }

    public Common(String packageName, String moduleName, String author, String version, String dateTime) {
        this.packageName = packageName;
        this.moduleName = moduleName;
        this.author = author;
        this.version = version;
        this.dateTime = dateTime;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Common{" +
                "packageName='" + packageName + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", author='" + author + '\'' +
                ", version='" + version + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
