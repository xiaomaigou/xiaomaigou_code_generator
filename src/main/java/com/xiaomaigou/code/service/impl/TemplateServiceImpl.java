package com.xiaomaigou.code.service.impl;

import com.xiaomaigou.code.config.GeneratorConfig;
import com.xiaomaigou.code.dto.Result;
import com.xiaomaigou.code.service.TemplateService;
import com.xiaomaigou.code.utils.FileUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:53
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private static final Logger logger = LoggerFactory.getLogger(TemplateServiceImpl.class);

    @Override
    public List<String> getAllTemplateName() {
        File templatesDirectory = this.getTemplatesDirectory();
        if (templatesDirectory == null || !templatesDirectory.isDirectory()) {
            return null;
        }
        File[] listFiles = templatesDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        List<String> allTemplateName = new ArrayList<>();
        if (listFiles != null && listFiles.length > 0) {
            for (File file : listFiles) {
                allTemplateName.add(file.getName());
            }
        }
        return allTemplateName;
    }

    @Override
    public List<String> getTemplatesByTemplateName(String templateName) {
        File templatesDirectory = this.getTemplatesDirectory();
        if (templatesDirectory == null || !templatesDirectory.isDirectory()) {
            return null;
        }

        File file = new File(templatesDirectory, templateName);
        if (!file.isDirectory()) {
            return null;
        }
        // 获取该模板的所有模板文件(包括子文件夹)
        Collection<File> templatesCollection = FileUtils.listFiles(file, new SuffixFileFilter(GeneratorConfig.FREEMARKER_SUFFIX), TrueFileFilter.INSTANCE);
        if (CollectionUtils.isNotEmpty(templatesCollection)) {
            List<String> templates = new ArrayList<>();
            // freemarker处理的模板名称只能是相对于模板目录的相对路径
            for (File templateFile : templatesCollection) {
                templates.add(StringUtils.substringAfter(templateFile.getPath(), templatesDirectory.getPath() + File.separator));
            }
            logger.debug(String.format("根据模板名称获取该模板的所有模板文件成功,模板名称templateName=[%s],模板文件templates=[%s]", templateName, templates.toString()));
            return templates;
        } else {
            logger.warn(String.format("根据模板名称没有获取该模板的所有模板文件,templateName=[%s]", templateName));
        }
        return null;
    }

    @Override
    public List<File> getNotTemplatesFileByTemplateName(String templateName) {
        List<File> notTemplatesFiles = null;
        File templatesDirectory = this.getTemplatesDirectory();
        if (templatesDirectory == null || !templatesDirectory.isDirectory()) {
            return null;
        }

        File file = new File(templatesDirectory, templateName);
        if (!file.isDirectory()) {
            return null;
        }
        // 获取该模板下所有非模板文件(包括子文件夹)
        Collection<File> notTemplatesFileCollection = FileUtils.listFiles(file, new IOFileFilter() {
            // 非模板文件
            @Override
            public boolean accept(File file) {
                return !isTemplateFile(file.getName());
            }

            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        }, TrueFileFilter.INSTANCE);
        if (CollectionUtils.isNotEmpty(notTemplatesFileCollection)) {
            notTemplatesFiles = new ArrayList<>(notTemplatesFileCollection);
        }
        return notTemplatesFiles;
    }

    @Override
    public List<String> getNotTemplatesNameByTemplateName(String templateName) {
        List<String> notTemplatesNames = null;
        List<File> notTemplatesFiles = this.getNotTemplatesFileByTemplateName(templateName);
        if (CollectionUtils.isNotEmpty(notTemplatesFiles)) {
            notTemplatesNames = new ArrayList<>();
            for (File notTemplatesFile : notTemplatesFiles) {
                // freemarker处理的模板名称只能是相对于模板目录的相对路径
                notTemplatesNames.add(notTemplatesFile.getPath());
            }
        }
        return notTemplatesNames;
    }

    @Override
    public File getTemplatesDirectory() {
        File templatesDirectory = null;
        String freemarkerTemplateLoaderPath = GeneratorConfig.FREEMARKER_TEMPLATE_LOADER_PATH;
        if (StringUtils.isNotBlank(freemarkerTemplateLoaderPath)) {
            // 去掉空格
            freemarkerTemplateLoaderPath = StringUtils.deleteWhitespace(freemarkerTemplateLoaderPath);
            // 获取到类型
            String type = StringUtils.substringBefore(freemarkerTemplateLoaderPath, ":");
            // 路径
            String path = StringUtils.substringAfter(freemarkerTemplateLoaderPath, ":");
            logger.debug(String.format("获取模板目录配置,类型type=[%s],路径path=[%s]", type, path));
            if (StringUtils.equalsIgnoreCase("file", type)) {
                // 如果是./方式开头的相对路径则从相对路径中去掉./
                if (StringUtils.startsWithIgnoreCase(path, "./")) {
                    path = StringUtils.substringAfter(path, "./");
                }
                logger.debug(String.format("路径的模板目录，path=[%s]", path));
                templatesDirectory = new File(path);
            } else if (StringUtils.equalsIgnoreCase("classpath", type)) {
                // TODO classpath类型
                logger.error(String.format("无法获取到模板信息，classpath类型,type=[%s],freemarkerTemplateLoaderPath=[%s]", type, freemarkerTemplateLoaderPath));
            } else {
                logger.error(String.format("无法获取到模板信息，不支持的格式,type=[%s],freemarkerTemplateLoaderPath=[%s]", type, freemarkerTemplateLoaderPath));
            }
        }
        if (templatesDirectory != null) {
            logger.debug(String.format("获取模板目录成功,freemarkerTemplateLoaderPath=[%s],模板加载路径templatesDirectory=[%s]", freemarkerTemplateLoaderPath, templatesDirectory.getAbsolutePath()));
        } else {
            logger.warn(String.format("没有获取模板目录,请检查配置是否正确!freemarkerTemplateLoaderPath=[%s]", freemarkerTemplateLoaderPath));
        }
        return templatesDirectory;
    }

    @Override
    public Boolean isTemplateFile(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            return Boolean.FALSE;
        }
        return StringUtils.endsWithIgnoreCase(fileName, GeneratorConfig.FREEMARKER_SUFFIX);
    }

    @Override
    public Result<String> uploadTemplate(MultipartFile multipartFile) {
        Result<String> result = new Result<>();
        File templatesDirectory = this.getTemplatesDirectory();
        if (templatesDirectory == null || !templatesDirectory.isDirectory()) {
            return result.notFound("上传模板失败,未配置模板文件目录,请先配置模板文件目录后重新上传!");
        }
        // 文件保存的绝对路径
        String absoluteFilePath = templatesDirectory.getAbsolutePath();
        // 文件后缀
//        String suffix = StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
        File file = FileUtil.uploadFile(absoluteFilePath, multipartFile, multipartFile.getOriginalFilename());
        if (file != null) {
            result.success("上传模板成功!", "上传模板成功!");
            return result;
        } else {
            return result.fail("上传模板失败!");
        }
    }
}
