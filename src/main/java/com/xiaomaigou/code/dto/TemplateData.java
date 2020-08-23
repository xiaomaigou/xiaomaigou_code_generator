package com.xiaomaigou.code.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 模板数据
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 12:43
 */
@ApiModel(description = "模板数据")
public class TemplateData implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 公共属性
     */
    @ApiModelProperty(value = "公共属性", name = "common")
    private Common common;

    /**
     * 表属性
     */
    @ApiModelProperty(value = "表属性", name = "table")
    private Table table;

    /**
     * 使用的模板名称
     */
    @ApiModelProperty(value = "使用的模板名称", name = "useTemplateName")
    private String useTemplateName;

    public TemplateData() {
    }

    public TemplateData(Common common, Table table, String useTemplateName) {
        this.common = common;
        this.table = table;
        this.useTemplateName = useTemplateName;
    }

    public Common getCommon() {
        return common;
    }

    public void setCommon(Common common) {
        this.common = common;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getUseTemplateName() {
        return useTemplateName;
    }

    public void setUseTemplateName(String useTemplateName) {
        this.useTemplateName = useTemplateName;
    }

    @Override
    public String toString() {
        return "TemplateData{" +
                "common=" + common +
                ", table=" + table +
                ", useTemplateName='" + useTemplateName + '\'' +
                '}';
    }
}
