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
    @ApiModelProperty(value = "主键（主键：true）", name = "isPrimaryKey")
    private Boolean isPrimaryKey;

    /**
     * 不能为空
     */
    @ApiModelProperty(value = "不能为空(不能为空：true)", name = "notNull")
    private Boolean notNull;

    public void setExtra(String extra) {
        this.extra = extra;
        if (isAutoIncrement == null) {
            isAutoIncrement = StringUtils.equalsIgnoreCase(this.getExtra(), "auto_increment");
        }
    }

    public void setIsNullable(String isNullable) {
        this.isNullable = isNullable;
        if (notNull == null) {
            notNull = StringUtils.equalsIgnoreCase(this.getIsNullable(), "NO");
        }
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
        if (isPrimaryKey == null) {
            isPrimaryKey = StringUtils.equalsAnyIgnoreCase(this.getColumnKey(), "PRI");
        }
    }

    public Boolean getAutoIncrement() {
        if (isAutoIncrement == null) {
            isAutoIncrement = StringUtils.equalsIgnoreCase(this.getExtra(), "auto_increment");
        }
        return isAutoIncrement;
    }

    public Boolean getPrimaryKey() {
        if (isPrimaryKey == null) {
            isPrimaryKey = StringUtils.equalsAnyIgnoreCase(this.getColumnKey(), "PRI");
        }
        return isPrimaryKey;
    }

    public Boolean getNotNull() {
        if (notNull == null) {
            notNull = StringUtils.equalsIgnoreCase(this.getIsNullable(), "NO");
        }
        return notNull;
    }

    public Column() {
    }

    public Column(String tableName, String columnName, String dataType, String columnComment, String isNullable, String columnKey, String extra, String attrName, String attrname, String attrType, Boolean isAutoIncrement, Boolean isPrimaryKey, Boolean notNull) {
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
        this.isPrimaryKey = isPrimaryKey;
        this.notNull = notNull;
        if (isAutoIncrement == null) {
            this.isAutoIncrement = StringUtils.equalsIgnoreCase(this.getExtra(), "auto_increment");
        }
        if (isPrimaryKey == null) {
            this.isPrimaryKey = StringUtils.equalsAnyIgnoreCase(this.getColumnKey(), "PRI");
        }
        if (notNull == null) {
            this.notNull = StringUtils.equalsIgnoreCase(this.getIsNullable(), "NO");
        }

        // 总是返回空字符串
        this.columnComment = StringUtils.defaultString(this.columnComment);
        // 去掉换行符
        this.columnComment = StringUtils.replace(this.columnComment, StringUtils.LF, "");
        this.columnComment = StringUtils.replace(this.columnComment, StringUtils.CR, "");
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
        columnComment = StringUtils.defaultString(columnComment);
        // 去掉换行符
        columnComment = StringUtils.replace(columnComment, StringUtils.LF, "");
        columnComment = StringUtils.replace(columnComment, StringUtils.CR, "");
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        // 总是返回空字符串
        columnComment = StringUtils.defaultString(columnComment);
        // 去掉换行符
        columnComment = StringUtils.replace(columnComment, StringUtils.LF, "");
        columnComment = StringUtils.replace(columnComment, StringUtils.CR, "");
        this.columnComment = columnComment;
    }

    public String getIsNullable() {
        return isNullable;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public String getExtra() {
        return extra;
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
//        return isPrimaryKey;
//    }

    public void setPrimaryKey(Boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

//    public Boolean getNotNull() {
//        return notNull;
//    }

    public void setNotNull(Boolean notNull) {
        this.notNull = notNull;
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
                ", isAutoIncrement=" + this.getAutoIncrement() +
                ", isPrimaryKey=" + this.getPrimaryKey() +
                ", notNull=" + this.getNotNull() +
                '}';
    }
}
