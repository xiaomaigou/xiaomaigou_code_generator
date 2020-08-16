package com.xiaomaigou.code.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 字段属性
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 12:47
 */
@ApiModel(description = "字段属性")
public class Column implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 表的名称
     */
    @ApiModelProperty(value = "表的名称", name = "tableName")
    private String tableName;
    /**
     * 字段名称
     */
    @ApiModelProperty(value = "字段名称", name = "columnName")
    private String columnName;
    /**
     * 字段类型
     */
    @ApiModelProperty(value = "字段类型", name = "dataType")
    private String dataType;
    /**
     * 字段备注
     */
    @ApiModelProperty(value = "字段备注", name = "columnComment")
    private String columnComment;

    /**
     * 是否允许为空(允许为空：YES，不能为空：NO;注意：该字段为String类型，如果需要判断该字段是否允许为null请使用notNull字段代替)
     */
    @ApiModelProperty(value = "是否允许为空(允许为空：YES，不能为空：NO;注意：该字段为String类型，如果需要判断该字段是否允许为null请使用notNull字段代替)", name = "isNullable")
    private String isNullable;

    /**
     * 主键（主键：PRI;注意：该字段为String类型，如果需要判断该字段是否为主键请使用isPrimaryKey字段代替）
     */
    @ApiModelProperty(value = "主键（主键：PRI;注意：该字段为String类型，如果需要判断该字段是否为主键请使用isPrimaryKey字段代替）", name = "columnKey")
    private String columnKey;

    /**
     * 是否为自增(自增：auto_increment;注意：该字段为String类型，如果需要判断该字段是否为自增请使用isAutoIncrement字段代替)
     */
    @ApiModelProperty(value = "是否为自增(自增：auto_increment;注意：该字段为String类型，如果需要判断该字段是否为自增请使用isAutoIncrement字段代替)", name = "extra")
    private String extra;

    /**
     * 字段名称(第一个字母大写)，如：user_name => UserName
     */
    @ApiModelProperty(value = "字段名称(第一个字母大写)，如：user_name => UserName", name = "attrName")
    private String attrName;
    /**
     * 属性名称(第一个字母小写)，如：user_name => userName
     */
    @ApiModelProperty(value = "属性名称(第一个字母小写)，如：user_name => userName", name = "attrname")
    private String attrname;
    /**
     * 属性类型
     */
    @ApiModelProperty(value = "属性类型", name = "attrType")
    private String attrType;

    /**
     * 是否自增
     */
    @ApiModelProperty(value = "是否为自增(自增：true)", name = "isAutoIncrement")
    private Boolean isAutoIncrement;

    /**
     * 是否主键
     */
    @ApiModelProperty(value = "主键（主键：true）", name = "primaryKey")
    private Boolean primaryKey;

    /**
     * 不能为空
     */
    @ApiModelProperty(value = "不能为空(不能为空：true)", name = "notNull")
    private Boolean notNull;

    /**
     * 是否作为搜索字段(作为搜索字段：true)
     */
    @ApiModelProperty(value = "是否作为搜索字段(作为搜索字段：true)", name = "search")
    private Boolean search;

    public Column() {
    }

    public Column(String tableName, String columnName, String dataType, String columnComment, String isNullable, String columnKey, String extra, String attrName, String attrname, String attrType, Boolean isAutoIncrement, Boolean primaryKey, Boolean notNull, Boolean search) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.isNullable = isNullable;
        this.columnKey = columnKey;
        this.extra = extra;
        this.attrName = attrName;
        this.attrname = attrname;
        this.attrType = attrType;
        this.isAutoIncrement = isAutoIncrement;
        this.primaryKey = primaryKey;
        this.notNull = notNull;
        this.search = search;
    }

    public Boolean getAutoIncrement() {
        if (isAutoIncrement == null) {
            isAutoIncrement = StringUtils.equalsIgnoreCase(this.getExtra(), "auto_increment");
        }
        return isAutoIncrement;
    }

    public Boolean getNotNull() {
        if (notNull == null) {
            notNull = StringUtils.equalsIgnoreCase(this.getIsNullable(), "NO");
        }
        return notNull;
    }

    public Boolean getPrimaryKey() {
        if (primaryKey == null) {
            primaryKey = StringUtils.equalsAnyIgnoreCase(this.getColumnKey(), "PRI");
        }
        return primaryKey;
    }

    public Boolean getSearch() {
        if (search == null) {
            search = !this.getPrimaryKey();
        }
        return search;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
        // 总是返回空字符串
        columnComment = StringUtils.defaultString(columnComment, StringUtils.EMPTY);
        // 去掉换行符
        columnComment = StringUtils.replace(columnComment, StringUtils.LF, StringUtils.EMPTY);
        columnComment = StringUtils.replace(columnComment, StringUtils.CR, StringUtils.EMPTY);
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {
        return attrname;
    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    public String getAttrType() {
        return attrType;
    }

    public void setAttrType(String attrType) {
        this.attrType = attrType;
    }

//    public Boolean getAutoIncrement() {
//        return isAutoIncrement;
//    }

    public void setAutoIncrement(Boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

//    public Boolean getPrimaryKey() {
//        return primaryKey;
//    }

    public void setPrimaryKey(Boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

//    public Boolean getNotNull() {
//        return notNull;
//    }

    public void setNotNull(Boolean notNull) {
        this.notNull = notNull;
    }

//    public Boolean getSearch() {
//        return search;
//    }

    public void setSearch(Boolean search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "Column{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", dataType='" + dataType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", extra='" + extra + '\'' +
                ", attrName='" + attrName + '\'' +
                ", attrname='" + attrname + '\'' +
                ", attrType='" + attrType + '\'' +
                ", isAutoIncrement=" + isAutoIncrement +
                ", primaryKey=" + primaryKey +
                ", notNull=" + notNull +
                ", search=" + search +
                '}';
    }
}
