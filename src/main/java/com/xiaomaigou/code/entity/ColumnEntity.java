package com.xiaomaigou.code.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 字段实体类
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 12:20
 */
@ApiModel(description = "字段实体类")
public class ColumnEntity implements Serializable {

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
     * 字段序号
     */
    @ApiModelProperty(value = "字段序号", name = "ordinalPosition")
    private Integer ordinalPosition;
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
     * 是否允许为空(允许为空：YES，不能为空：NO)
     */
    @ApiModelProperty(value = "是否允许为空(允许为空：YES，不能为空：NO)", name = "isNullable")
    private String isNullable;

    /**
     * 主键（主键：PRI）
     */
    @ApiModelProperty(value = "主键（主键：PRI）", name = "columnKey")
    private String columnKey;

    /**
     * 是否为自增(自增：auto_increment)
     */
    @ApiModelProperty(value = "是否为自增(自增：auto_increment)", name = "extra")
    private String extra;

    public ColumnEntity() {
    }

    public ColumnEntity(String tableName, String columnName, Integer ordinalPosition, String dataType, String columnComment, String isNullable, String columnKey, String extra) {
        this.tableName = tableName;
        this.columnName = columnName;
        this.ordinalPosition = ordinalPosition;
        this.dataType = dataType;
        this.columnComment = columnComment;
        this.isNullable = isNullable;
        this.columnKey = columnKey;
        this.extra = extra;
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

    public Integer getOrdinalPosition() {
        return ordinalPosition;
    }

    public void setOrdinalPosition(Integer ordinalPosition) {
        this.ordinalPosition = ordinalPosition;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getColumnComment() {
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

    @Override
    public String toString() {
        return "ColumnEntity{" +
                "tableName='" + tableName + '\'' +
                ", columnName='" + columnName + '\'' +
                ", ordinalPosition=" + ordinalPosition +
                ", dataType='" + dataType + '\'' +
                ", columnComment='" + columnComment + '\'' +
                ", isNullable='" + isNullable + '\'' +
                ", columnKey='" + columnKey + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
