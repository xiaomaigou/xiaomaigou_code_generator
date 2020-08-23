package com.xiaomaigou.code.service;

import java.io.File;
import java.util.List;

/**
 * 代码模板
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:44
 */
public interface TemplateService {

    /**
     * 获取所有模板名称
     *
     * @return 所有模板名称
     */
    List<String> getAllTemplateName();

    /**
     * 根据模板名称获取该模板的所有模板文件路径
     *
     * @param templateName 模板名称
     * @return 该模板的所有模板文件路径
     */
    List<String> getTemplatesByTemplateName(String templateName);

    /**
     * 根据模板名称获取该模板下所有非模板文件(文件格式)
     *
     * @param templateName 模板名称
     * @return 该模板的所有非模板文件(文件格式)
     */
    List<File> getNotTemplatesFileByTemplateName(String templateName);

    /**
     * 根据模板名称获取该模板下所有非模板文件名称(文件名称格式)
     *
     * @param templateName 模板名称
     * @return 该模板的所有非模板文件(文件名称格式)
     */
    List<String> getNotTemplatesNameByTemplateName(String templateName);

    /**
     * 获取模板目录
     *
     * @return 模板目录
     */
    File getTemplatesDirectory();

    /**
     * 根据文件名称判断是否为模板文件
     *
     * @param fileName 文件名称
     * @return 是否为模板文件
     */
    Boolean isTemplateFile(String fileName);
}
