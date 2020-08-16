package com.xiaomaigou.code.service.impl;

import com.xiaomaigou.code.config.GeneratorConfig;
import com.xiaomaigou.code.dto.Column;
import com.xiaomaigou.code.dto.Common;
import com.xiaomaigou.code.dto.Table;
import com.xiaomaigou.code.dto.TemplateData;
import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;
import com.xiaomaigou.code.service.GenerateDataService;
import com.xiaomaigou.code.service.GeneratorConfigService;
import com.xiaomaigou.code.service.TableService;
import com.xiaomaigou.code.utils.ToJavaNameUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:48
 */
@Service
public class GenerateDataServiceImpl implements GenerateDataService {

    private static final Logger logger = LoggerFactory.getLogger(GenerateDataServiceImpl.class);

    @Autowired
    private GeneratorConfigService generatorConfigService;

    @Autowired
    private TableService tableService;

    @Override
    public TemplateData generateTemplateData(TableEntity tableEntity, List<ColumnEntity> columnEntityList) {
        // 公共属性
        Common common = this.generateCommonData();
        // 表属性
        Table table = this.generateTableData(tableEntity, columnEntityList);
        // 封装模板数据
        TemplateData templateData = new TemplateData();
        templateData.setCommon(common);
        templateData.setTable(table);
        return templateData;
    }

    @Override
    public List<TemplateData> generateTemplateData(List<String> tableNameList) {
        if (CollectionUtils.isEmpty(tableNameList)) {
            return null;
        }
        List<TemplateData> templateDataList = new ArrayList<>();
        for (String tableName : tableNameList) {
            // 根据表名查询表详细信息
            TableEntity tableEntity = tableService.findTableByTableName(tableName);
            // 根据表名查询列详细信息
            List<ColumnEntity> columnEntityList = tableService.findColumnsByTableName(tableName);
            if (tableEntity == null || CollectionUtils.isEmpty(columnEntityList)) {
                break;
            }
            // 生成代码
            TemplateData templateData = this.generateTemplateData(tableEntity, columnEntityList);
            templateDataList.add(templateData);
        }
        return templateDataList;
    }

    @Override
    public Table generateTableData(TableEntity tableEntity, List<ColumnEntity> columnEntityList) {
        if (tableEntity == null || CollectionUtils.isEmpty(columnEntityList)) {
            return null;
        }
        // 获取代码生成器配置信息
        Configuration generatorConfig = generatorConfigService.getGeneratorConfig();

        Table table = new Table();
        table.setTableName(tableEntity.getTableName());
        // 表名转换为Java类名
        String defaultClassName = ToJavaNameUtil.tableNameToJavaClassName(table.getTableName(), generatorConfig.getStringArray("tablePrefix"));
        String defaultTableComment = tableEntity.getTableComment();
        if (StringUtils.isNotBlank(defaultTableComment)) {
            String[] tableComments = defaultTableComment.split(GeneratorConfig.COMMENT_REGEX);
            // 如果长度大于0，并且第一个元素全部为字母且不为空
            if (tableComments.length > 0) {
                // 删除空白字符
                String name = StringUtils.deleteWhitespace(tableComments[0]);
                // 判断是否全部为字母（注意：这里不能使用StringUtils.isAlpha()直接判断，该方法为判断是否全部为字符，而不是判断是否全部为字母，该方法为将汉字判断true）
                if (StringUtils.isAllLowerCase(StringUtils.lowerCase(name))) {
                    // 从备注中去掉重命名的Java名
                    defaultTableComment = StringUtils.substringAfter(defaultTableComment, tableComments[0] + GeneratorConfig.COMMENT_REGEX);
                    // 设置新的Java名
                    defaultClassName = name;
                }
            }
        }
        table.setTableComment(defaultTableComment);
        table.setClassName(defaultClassName);
        table.setClassname(ToJavaNameUtil.nameToJavaAttrName(table.getClassName()));
        table.setPathName(ToJavaNameUtil.nameToJavaAttrName(table.getClassName()));

        boolean hasBigDecimal = false;
        boolean hasList = false;

        //列信息
        List<Column> columsList = new ArrayList<>();
        for (ColumnEntity columnEntity : columnEntityList) {
            Column column = new Column();
            column.setTableName(tableEntity.getTableName());
            column.setColumnName(columnEntity.getColumnName());
            column.setDataType(columnEntity.getDataType());
            column.setIsNullable(columnEntity.getIsNullable());
            column.setColumnKey(columnEntity.getColumnKey());
            // 列名转换成Java属性名
            String defaultAttrName = ToJavaNameUtil.columnNameToJavaAttrName(column.getColumnName());
            String defaultColumnComment = columnEntity.getColumnComment();
            // 拆分备注转换为Java属性名
            if (StringUtils.isNotBlank(defaultColumnComment)) {
                String[] columnComments = defaultColumnComment.split(GeneratorConfig.COMMENT_REGEX);
                // 如果长度大于0，并且第一个元素全部为字母且不为空
                if (columnComments.length > 0) {
                    // 删除空白字符
                    String name = StringUtils.deleteWhitespace(columnComments[0]);
                    // 判断是否全部为字母（注意：这里不能使用StringUtils.isAlpha()直接判断，该方法为判断是否全部为字符，而不是判断是否全部为字母，该方法为将汉字判断true）
                    if (StringUtils.isAllLowerCase(StringUtils.lowerCase(name))) {
                        // 从备注中去掉重命名的Java名
                        defaultColumnComment = StringUtils.substringAfter(defaultColumnComment, columnComments[0] + GeneratorConfig.COMMENT_REGEX);
                        // 设置新的Java名
                        defaultAttrName = name;
                    }
                }
            }
            column.setColumnComment(defaultColumnComment);
            column.setAttrName(defaultAttrName);
            column.setAttrname(ToJavaNameUtil.nameToJavaAttrName(column.getAttrName()));
            // 列的数据类型，转换成Java类型
            String attrType = generatorConfig.getString(column.getDataType(), ToJavaNameUtil.columnNameToJavaAttrName(column.getDataType()));
            column.setAttrType(attrType);
            column.setExtra(columnEntity.getExtra());

            if (!hasBigDecimal && StringUtils.equals(attrType, "BigDecimal")) {
                hasBigDecimal = true;
            }
            if (!hasList && "array".equals(column.getExtra())) {
                hasList = true;
            }
            // 是否主键
            if (column.getPrimaryKey() && table.getPrimaryKey() == null) {
                table.setPrimaryKey(column);
            }

            columsList.add(column);
        }
        table.setColumns(columsList);

        // 表属性
        // 没主键，则第一个字段为主键
        if ((table.getPrimaryKey() == null) && (CollectionUtils.isNotEmpty(table.getColumns()))) {
            table.setPrimaryKey(table.getColumns().get(0));
        }
        table.setHasBigDecimal(hasBigDecimal);
        table.setHasList(hasList);

        return table;
    }

    @Override
    public Common generateCommonData() {
        // 获取代码生成器配置信息
        Configuration generatorConfig = generatorConfigService.getGeneratorConfig();
        // 公共属性
        Common common = new Common();
        common.setPackageName(generatorConfig.getString("packageName"));
        common.setModuleName(generatorConfig.getString("moduleName"));
        common.setAuthor(generatorConfig.getString("author"));
        common.setVersion(generatorConfig.getString("version"));
        common.setDateTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
        return common;
    }
}
