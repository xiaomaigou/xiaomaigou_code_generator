package com.xiaomaigou.code.service.impl;

import com.xiaomaigou.code.config.GeneratorConfig;
import com.xiaomaigou.code.dto.Common;
import com.xiaomaigou.code.dto.GenerateCodeTemplateDataDTO;
import com.xiaomaigou.code.dto.Table;
import com.xiaomaigou.code.dto.TemplateData;
import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;
import com.xiaomaigou.code.service.GenerateCodeService;
import com.xiaomaigou.code.service.GenerateDataService;
import com.xiaomaigou.code.service.TableService;
import com.xiaomaigou.code.service.TemplateService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:45
 */
@Service
public class GenerateCodeServiceImpl implements GenerateCodeService {

    private static final Logger logger = LoggerFactory.getLogger(GenerateCodeServiceImpl.class);

    @Autowired
    private TableService tableService;

    @Autowired
    private TemplateService templateService;

    @Autowired
    private GenerateDataService generateDataService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public byte[] generateCode(List<String> tableNameList) {
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
            this.generateCode(tableEntity, columnEntityList, zipOutputStream);

        }
        IOUtils.closeQuietly(zipOutputStream);
        return outputStream.toByteArray();
    }

    @Override
    public byte[] generateCode(GenerateCodeTemplateDataDTO generateCodeTemplateDataDTO) {

        if (generateCodeTemplateDataDTO == null || CollectionUtils.isEmpty(generateCodeTemplateDataDTO.getTemplateDataList())) {
            return new byte[0];
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

        List<TemplateData> templateDataList = generateCodeTemplateDataDTO.getTemplateDataList();
        for (TemplateData templateData : templateDataList) {
            this.generateCode(templateData, zipOutputStream);
        }

        IOUtils.closeQuietly(zipOutputStream);
        return outputStream.toByteArray();
    }

    @Override
    public void generateCode(TemplateData templateData, ZipOutputStream zipOutputStream) {
        // 获取模板列表
        List<String> templates = templateService.getTemplates();
        if (templateData == null || zipOutputStream == null || CollectionUtils.isEmpty(templates)) {
            return;
        }
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        // 公共属性
        Common common = templateData.getCommon();
        // 表属性
        Table table = templateData.getTable();
        for (String templateString : templates) {
            try {
                Template template = configuration.getTemplate(templateString);
                String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);
                zipOutputStream.putNextEntry(new ZipEntry(table.getClassName() + StringUtils.substringBeforeLast(templateString, GeneratorConfig.FREEMARKER_SUFFIX)));
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

    @Override
    public void generateCode(TableEntity tableEntity, List<ColumnEntity> columnEntityList, ZipOutputStream zipOutputStream) {
        // 获取模板列表
        List<String> templates = templateService.getTemplates();
        if (tableEntity == null || CollectionUtils.isEmpty(columnEntityList) || zipOutputStream == null || CollectionUtils.isEmpty(templates)) {
            return;
        }

        // 公共属性
        Common common = generateDataService.generateCommonData();
        // 表属性
        Table table = generateDataService.generateTableData(tableEntity, columnEntityList);
        // 封装模板数据
        TemplateData templateData = new TemplateData();
        templateData.setCommon(common);
        templateData.setTable(table);

        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        for (String templateString : templates) {
            try {
                Template template = configuration.getTemplate(templateString);
                String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);
                zipOutputStream.putNextEntry(new ZipEntry(table.getClassName() + StringUtils.substringBeforeLast(templateString, GeneratorConfig.FREEMARKER_SUFFIX)));
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
}
