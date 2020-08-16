package com.xiaomaigou.code.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 生成代码模板数据
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:30
 */
@ApiModel(description = "生成代码模板数据")
public class GenerateCodeTemplateDataDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 模板数据 List
     */
    @ApiModelProperty(value = "模板数据 List", name = "templateDataList")
    private List<TemplateData> templateDataList;

    public GenerateCodeTemplateDataDTO() {
    }

    public GenerateCodeTemplateDataDTO(List<TemplateData> templateDataList) {
        this.templateDataList = templateDataList;
    }

    public List<TemplateData> getTemplateDataList() {
        return templateDataList;
    }

    public void setTemplateDataList(List<TemplateData> templateDataList) {
        this.templateDataList = templateDataList;
    }

    @Override
    public String toString() {
        return "GenerateCodeTemplateDataDTO{" +
                "templateDataList=" + templateDataList +
                '}';
    }
}
