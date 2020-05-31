package com.xiaomaigou.code.service.impl;

import com.xiaomaigou.code.config.GeneratorConfig;
import com.xiaomaigou.code.dto.Column;
import com.xiaomaigou.code.dto.Common;
import com.xiaomaigou.code.dto.Table;
import com.xiaomaigou.code.dto.TemplateData;
import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;
import com.xiaomaigou.code.service.GeneratorService;
import com.xiaomaigou.code.service.TableService;
import freemarker.template.Template;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 18:27
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {

    @Autowired
    private TableService tableService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public Configuration getGeneratorConfig() {
        try {
            return new PropertiesConfiguration(GeneratorConfig.PROPERTIES_CONFIG_PATH);
        } catch (ConfigurationException e) {
            throw new RuntimeException("获取配置信息失败!", e);
        }
    }

    @Override
    public List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("Controller.java.ftl");
        templates.add("DTO.java.ftl");
        templates.add("Entity.java.ftl");
        templates.add("Mapper.java.ftl");
        templates.add("Mapper.xml.ftl");
        templates.add("Service.java.ftl");
        templates.add("ServiceImpl.java.ftl");
        return templates;
    }

    @Override
    public byte[] generatorCode(List<String> tableNameList) {

        if (CollectionUtils.isEmpty(tableNameList)) {
            return new byte[0];
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        for (String tableName : tableNameList) {
            // 根据表名查询表详细信息
            TableEntity tableEntity = tableService.findTableByTableName(tableName);
            // 根据表名查询列详细信息
            List<ColumnEntity> columnEntityList = tableService.findColumnsByTableName(tableName);

            if (tableEntity == null || CollectionUtils.isEmpty(columnEntityList)) {
                break;
            }

            // 生成代码
            this.generatorCode(tableEntity, columnEntityList, zipOutputStream);

        }
        IOUtils.closeQuietly(zipOutputStream);
        return outputStream.toByteArray();
    }

    @Override
    public void generatorCode(TableEntity tableEntity, List<ColumnEntity> columnEntityList, ZipOutputStream zipOutputStream) {

        // 获取模板列表
        List<String> templates = this.getTemplates();
        if (CollectionUtils.isEmpty(templates)) {
            return;
        }

        // 获取代码生成器配置信息
        Configuration generatorConfig = this.getGeneratorConfig();

        // 公共属性
        Common common = new Common();
        common.setPackageName(generatorConfig.getString("packageName"));
        common.setModuleName(generatorConfig.getString("moduleName"));
        common.setAuthor(generatorConfig.getString("author"));
        common.setVersion(generatorConfig.getString("version"));
        common.setDateTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

        // 表属性
        Table table = new Table();
        table.setTableName(tableEntity.getTableName());
        table.setTableComment(tableEntity.getTableComment());
        // 表名转换为Java类名
        String className = this.tableNameToJavaName(table.getTableName(), generatorConfig.getStringArray("tablePrefix"));
        table.setClassName(className);
        table.setClassname(StringUtils.uncapitalize(className));
        table.setPathName(StringUtils.uncapitalize(className));

        boolean hasBigDecimal = false;
        boolean hasList = false;

        //列信息
        List<Column> columsList = new ArrayList<>();
        for (ColumnEntity columnEntity : columnEntityList) {
            Column column = new Column();
            column.setTableName(tableEntity.getTableName());
            column.setColumnName(columnEntity.getColumnName());
            column.setDataType(columnEntity.getDataType());
            column.setColumnComment(columnEntity.getColumnComment());
            column.setIsNullable(columnEntity.getIsNullable());
            column.setColumnKey(columnEntity.getColumnKey());
            //列名转换成Java属性名
            String attrName = this.columnNameToJavaName(column.getColumnName());
            column.setAttrName(attrName);
            column.setAttrname(StringUtils.uncapitalize(attrName));
            //列的数据类型，转换成Java类型
            String attrType = generatorConfig.getString(column.getDataType(), this.columnNameToJavaName(column.getDataType()));
            column.setAttrType(attrType);
            column.setExtra(columnEntity.getExtra());

            if (!hasBigDecimal && attrType.equals("BigDecimal")) {
                hasBigDecimal = true;
            }
            if (!hasList && "array".equals(column.getExtra())) {
                hasList = true;
            }
            // 是否主键
            if ("PRI".equalsIgnoreCase(columnEntity.getColumnKey()) && table.getPrimaryKey() == null) {
                table.setPrimaryKey(column);
            }

            columsList.add(column);
        }
        table.setColumns(columsList);

        // 没主键，则第一个字段为主键
        if (table.getPrimaryKey() == null) {
            table.setPrimaryKey(table.getColumns().get(0));
        }

        // 表属性
        table.setHasBigDecimal(hasBigDecimal);
//        table.setHasList(hasList);

        // 封装模板数据
        TemplateData templateData = new TemplateData();
        templateData.setCommon(common);
        templateData.setTable(table);

        freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
        for (String templateString : templates) {
            try {
                Template template = configuration.getTemplate(templateString);

                String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);
                zipOutputStream.putNextEntry(new ZipEntry(table.getClassName() + templateString.replace(".ftl", "")));
                IOUtils.write(result, zipOutputStream, "UTF-8");
                zipOutputStream.closeEntry();

//                Writer out = new FileWriter(tableEntity.getClassName()+templateString.replace(".ftl", ""));
//                template.process(map, out);
//                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 表名转换为Java类名
     *
     * @param tableName        表名
     * @param tablePrefixArray 表前缀
     * @return Java类名
     */
    public String tableNameToJavaName(String tableName, String[] tablePrefixArray) {
        // 首先去掉表前缀
        if (tablePrefixArray != null && tablePrefixArray.length > 0) {
            for (String tablePrefix : tablePrefixArray) {
                tableName = tableName.replace(tablePrefix, "");
            }
        }
        // 驼峰命名转换
        return WordUtils.capitalizeFully(tableName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 字段名转换为Java属性名
     *
     * @param columnName 字段名
     * @return Java属性名
     */
    public String columnNameToJavaName(String columnName) {
        // 驼峰命名转换
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }
}
