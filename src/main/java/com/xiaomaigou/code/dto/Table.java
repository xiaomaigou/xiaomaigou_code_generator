package com.xiaomaigou.code.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 表属性
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 12:46
 */
@ApiModel(description = "表属性")
public class Table implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 表的名称
     */
    @ApiModelProperty(value = "表的名称", name = "tableName")
    private String tableName;

    /**
     * 表的备注
     */
    @ApiModelProperty(value = "表的备注", name = "tableComment")
    private String tableComment;

    /**
     * 表的主键
     */
    @ApiModelProperty(value = "表的主键", name = "primaryKey")
    private Column primaryKey;

    /**
     * 表的字段
     */
    @ApiModelProperty(value = "表的字段", name = "columns")
    private List<Column> columns;

    /**
     * 类名(第一个字母大写)，如：sys_user => SysUser
     */
    @ApiModelProperty(value = "类名(第一个字母大写)，如：sys_user => SysUser", name = "className")
    private String className;

    /**
     * 类名(第一个字母小写)，如：sys_user => sysUser
     */
    @ApiModelProperty(value = "类名(第一个字母小写)，如：sys_user => sysUser", name = "classname")
    private String classname;

    /**
     * 路径
     */
    @ApiModelProperty(value = "路径", name = "pathName")
    private String pathName;

    /**
     * 原始表的备注
     */
    @ApiModelProperty(value = "原始表的备注", name = "originalTableComment")
    private String originalTableComment;

    /**
     * 原始类名(第一个字母大写)，如：sys_user => SysUser
     */
    @ApiModelProperty(value = "原始类名(第一个字母大写)，如：sys_user => SysUser", name = "originalClassName")
    private String originalClassName;

    /**
     * 原始类名(第一个字母小写)，如：sys_user => sysUser
     */
    @ApiModelProperty(value = "原始类名(第一个字母小写)，如：sys_user => sysUser", name = "originalClassname")
    private String originalClassname;

    /**
     * 原始路径
     */
    @ApiModelProperty(value = "原始路径", name = "originalPathName")
    private String originalPathName;

    /**
     * 是否包含BigDecimal
     */
    @ApiModelProperty(value = "是否包含BigDecimal", name = "hasBigDecimal")
    private Boolean hasBigDecimal;

    public Table() {
    }

    public Table(String tableName, String tableComment, Column primaryKey, List<Column> columns, String className, String classname, String pathName, String originalTableComment, String originalClassName, String originalClassname, String originalPathName, Boolean hasBigDecimal) {
        this.tableName = tableName;
        this.tableComment = tableComment;
        this.primaryKey = primaryKey;
        this.columns = columns;
        this.className = className;
        this.classname = classname;
        this.pathName = pathName;
        this.originalTableComment = originalTableComment;
        this.originalClassName = originalClassName;
        this.originalClassname = originalClassname;
        this.originalPathName = originalPathName;
        this.hasBigDecimal = hasBigDecimal;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Column getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(Column primaryKey) {
        this.primaryKey = primaryKey;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getOriginalTableComment() {
        return originalTableComment;
    }

    public void setOriginalTableComment(String originalTableComment) {
        this.originalTableComment = originalTableComment;
    }

    public String getOriginalClassName() {
        return originalClassName;
    }

    public void setOriginalClassName(String originalClassName) {
        this.originalClassName = originalClassName;
    }

    public String getOriginalClassname() {
        return originalClassname;
    }

    public void setOriginalClassname(String originalClassname) {
        this.originalClassname = originalClassname;
    }

    public String getOriginalPathName() {
        return originalPathName;
    }

    public void setOriginalPathName(String originalPathName) {
        this.originalPathName = originalPathName;
    }

    public Boolean getHasBigDecimal() {
        return hasBigDecimal;
    }

    public void setHasBigDecimal(Boolean hasBigDecimal) {
        this.hasBigDecimal = hasBigDecimal;
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", primaryKey=" + primaryKey +
                ", columns=" + columns +
                ", className='" + className + '\'' +
                ", classname='" + classname + '\'' +
                ", pathName='" + pathName + '\'' +
                ", originalTableComment='" + originalTableComment + '\'' +
                ", originalClassName='" + originalClassName + '\'' +
                ", originalClassname='" + originalClassname + '\'' +
                ", originalPathName='" + originalPathName + '\'' +
                ", hasBigDecimal=" + hasBigDecimal +
                '}';
    }
}
