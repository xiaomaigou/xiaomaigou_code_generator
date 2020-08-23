package com.xiaomaigou.code.service;

import com.xiaomaigou.code.dto.Common;
import com.xiaomaigou.code.dto.Table;
import com.xiaomaigou.code.dto.TemplateData;
import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;

import java.util.List;

/**
 * 生成模板数据
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:42
 */
public interface GenerateDataService {

    /**
     * 生成模板数据
     *
     * @param tableNameList   表名List
     * @param useTemplateName 使用的模板名称
     * @return 模板数据
     */
    List<TemplateData> generateTemplateData(List<String> tableNameList, String useTemplateName);

    /**
     * 生成模板数据
     *
     * @param tableEntity      表详细信息
     * @param columnEntityList 列详细信息列表
     * @param useTemplateName  使用的模板名称
     * @return 模板数据
     */
    TemplateData generateTemplateData(TableEntity tableEntity, List<ColumnEntity> columnEntityList, String useTemplateName);

    /**
     * 生成表数据
     *
     * @param tableEntity      表详细信息
     * @param columnEntityList 列详细信息列表
     * @return 表数据
     */
    Table generateTableData(TableEntity tableEntity, List<ColumnEntity> columnEntityList);

    /**
     * 生成公共属性数据
     *
     * @param table 表数据
     * @return 公共属性数据
     */
    Common generateCommonData(Table table);

    /**
     * 获取默认使用的模板
     *
     * @return 默认使用的模板
     */
    String defaultUseTemplateName();
}
