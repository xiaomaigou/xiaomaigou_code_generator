package com.xiaomaigou.code.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    private TemplateService templateService;

    @Autowired
    private GenerateDataService generateDataService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public byte[] generateCode(List<String> tableNameList, String useTemplateName) {
        // 生成模板数据
        List<TemplateData> templateDataList = generateDataService.generateTemplateData(tableNameList, useTemplateName);
        // 生成代码模板数据
        GenerateCodeTemplateDataDTO generateCodeTemplateDataDTO = new GenerateCodeTemplateDataDTO();
        generateCodeTemplateDataDTO.setTemplateDataList(templateDataList);
        // 生成代码
        return this.generateCode(generateCodeTemplateDataDTO);
    }

    @Override
    public byte[] generateCode(TableEntity tableEntity, List<ColumnEntity> columnEntityList, String useTemplateName) {
        // 封装模板数据
        TemplateData templateData = generateDataService.generateTemplateData(tableEntity, columnEntityList, useTemplateName);
        // 生成代码模板数据
        List<TemplateData> templateDataList = new ArrayList<>();
        templateDataList.add(templateData);
        GenerateCodeTemplateDataDTO generateCodeTemplateDataDTO = new GenerateCodeTemplateDataDTO();
        generateCodeTemplateDataDTO.setTemplateDataList(templateDataList);
        // 生成代码
        return this.generateCode(generateCodeTemplateDataDTO);
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
        // 生成本次代码模板数据
        try {
            zipOutputStream.putNextEntry(new ZipEntry("generateCodeTemplateData.json"));
            IOUtils.write(JSONObject.toJSONString(generateCodeTemplateDataDTO, SerializerFeature.DisableCircularReferenceDetect), zipOutputStream, "UTF-8");
            zipOutputStream.closeEntry();
        } catch (IOException e) {
            logger.error(String.format("生成本次代码模板数据文件失败!generateCodeTemplateDataDTO=[%s]", JSONObject.toJSONString(generateCodeTemplateDataDTO)), e);
        }
        IOUtils.closeQuietly(zipOutputStream);
        return outputStream.toByteArray();
    }

    @Override
    public void generateCode(TemplateData templateData, ZipOutputStream zipOutputStream) {
        if (templateData == null || zipOutputStream == null) {
            return;
        }
        if (StringUtils.isBlank(templateData.getUseTemplateName())) {
            templateData.setUseTemplateName(generateDataService.defaultUseTemplateName());
        }
        // 获取模板列表
        List<String> templates = templateService.getTemplatesByTemplateName(templateData.getUseTemplateName());
        if (CollectionUtils.isEmpty(templates)) {
            return;
        }
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        // 公共属性
        Common common = templateData.getCommon();
        // 表属性
        Table table = templateData.getTable();
        for (String templateName : templates) {
            try {
                Template template = configuration.getTemplate(templateName);
                String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);
                // 根据原始文件名称作为模板生成新的文件名称和路径
                String fileName = this.generateStringCodeByStringTemplate(templateData, templateName);
                if (StringUtils.isNotBlank(fileName)) {
                    fileName = StringUtils.substringBeforeLast(fileName, GeneratorConfig.FREEMARKER_SUFFIX);
                } else {
                    fileName = table.getClassName() + StringUtils.substringBeforeLast(templateName, GeneratorConfig.FREEMARKER_SUFFIX);
                }
                if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(result)) {
                    break;
                }
                try {
                    // 如果抛异常，说明该文件已经存在，无需重新生成
                    zipOutputStream.putNextEntry(new ZipEntry(fileName));
                } catch (IOException e) {
                    logger.warn(String.format("该文件已存在,无需重复生成!fileName=[%s]", fileName));
                    break;
                }
                IOUtils.write(result, zipOutputStream, "UTF-8");
                zipOutputStream.closeEntry();

//                Writer out = new FileWriter(tableEntity.getClassName()+templateString.replace(".ftl", ""));
//                template.process(map, out);
//                out.close();
            } catch (Exception e) {
                logger.error(String.format("生成代码失败!templateData=[%s]", JSONObject.toJSONString(templateData)), e);
            }
        }

        // 生成非模板文件代码
        this.generateNotTemplatesCode(templateData, zipOutputStream);
    }

    @Override
    public String generateStringCodeByStringTemplate(TemplateData templateData, String stringTemplate) {
        if (templateData == null || StringUtils.isBlank(stringTemplate)) {
            return null;
        }
        Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        configuration.setTemplateLoader(new StringTemplateLoader());
        try {
            Template template = new Template("stringTemplate", stringTemplate, configuration);
            String result = FreeMarkerTemplateUtils.processTemplateIntoString(template, templateData);
            return result;
        } catch (Exception e) {
            logger.error(String.format("根据模板数据和字符串模板生成代码字符串失败!templateData=[%s]", JSONObject.toJSONString(templateData)), e);
        }
        return null;
    }

    @Override
    public void generateNotTemplatesCode(TemplateData templateData, ZipOutputStream zipOutputStream) {
        if (templateData == null || zipOutputStream == null) {
            return;
        }
        if (StringUtils.isBlank(templateData.getUseTemplateName())) {
            templateData.setUseTemplateName(generateDataService.defaultUseTemplateName());
        }
        // 获取模板目录
        File templatesDirectory = templateService.getTemplatesDirectory();
        if (templatesDirectory == null || !templatesDirectory.isDirectory()) {
            return;
        }
        // 获取非模板文件
        List<File> notTemplatesFiles = templateService.getNotTemplatesFileByTemplateName(templateData.getUseTemplateName());
        if (CollectionUtils.isEmpty(notTemplatesFiles)) {
            return;
        }
        for (File notTemplatesFile : notTemplatesFiles) {
            // 模板名称为相对于模板目录的相对路径
            String templateName = StringUtils.substringAfter(notTemplatesFile.getPath(), templatesDirectory.getPath() + File.separator);
            // 根据原始文件名称作为模板生成新的文件名称和路径
            String fileName = this.generateStringCodeByStringTemplate(templateData, templateName);
            if (StringUtils.isBlank(fileName)) {
                fileName = templateName;
            }
            if (StringUtils.isEmpty(fileName)) {
                break;
            }
            try {
                try {
                    // 如果抛异常，说明该文件已经存在，无需重新生成
                    zipOutputStream.putNextEntry(new ZipEntry(fileName));
                } catch (IOException e) {
                    logger.warn(String.format("该文件已存在,无需重复生成!fileName=[%s]", fileName));
                    break;
                }
                IOUtils.write(FileUtils.readFileToByteArray(notTemplatesFile), zipOutputStream);
                zipOutputStream.closeEntry();
            } catch (Exception e) {
                logger.error(String.format("生成非模板文件代码失败!templateData=[%s]", JSONObject.toJSONString(templateData)), e);
            }
        }
    }
}
