package com.xiaomaigou.code.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 表实体类
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 12:17
 */
@ApiModel(description = "表实体类")
public class TableEntity implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 表的名称
     */
    @ApiModelProperty(value = "表的名称", name = "tableName")
    private String tableName;
    /**
     * 数据库引擎
     */
    @ApiModelProperty(value = "数据库引擎", name = "engine")
    private String engine;
    /**
     * 表的备注
     */
    @ApiModelProperty(value = "表的备注", name = "tableComment")
    private String tableComment;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间", name = "createTime")
    private Date createTime;

    public TableEntity() {
    }

    public TableEntity(String tableName, String engine, String tableComment, Date createTime) {
        this.tableName = tableName;
        this.engine = engine;
        this.tableComment = tableComment;
        this.createTime = createTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "tableName='" + tableName + '\'' +
                ", engine='" + engine + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
