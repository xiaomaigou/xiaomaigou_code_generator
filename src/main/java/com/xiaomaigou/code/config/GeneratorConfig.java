package com.xiaomaigou.code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 代码生成器配置
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 18:54
 */
@Configuration
public class GeneratorConfig implements Serializable {

    private static final Long serialVersionUID = 1L;

    /**
     * 数据库类型，可选类型:[mysql、oracle、sqlserver、postgresql、mongodb]
     * xiaomaigou.code.generator.datasource.type
     */
    public static String DATASOURCE_TYPE;

    /**
     * 代码生成器配置文件路径
     * xiaomaigou.code.generator.properties.config_path
     */
    public static String PROPERTIES_CONFIG_PATH;

    /**
     * freemarker模板文件后缀，比如".ftl"
     * spring.freemarker.suffix
     */
    public static String FREEMARKER_SUFFIX;

    /**
     * 从备注中转换为表名或者字段名称的分隔符，比如";"
     * xiaomaigou.code.generator.comment.regex
     */
    public static String COMMENT_REGEX;

    @Value("${xiaomaigou.code.generator.datasource.type}")
    public void setDatasourceType(String datasourceType) {
        DATASOURCE_TYPE = datasourceType;
    }

    @Value("${xiaomaigou.code.generator.properties.config_path}")
    public void setPropertiesConfigPath(String propertiesConfigPath) {
        PROPERTIES_CONFIG_PATH = propertiesConfigPath;
    }

    @Value("${spring.freemarker.suffix}")
    public void setFreemarkerSuffix(String freemarkerSuffix) {
        FREEMARKER_SUFFIX = freemarkerSuffix;
    }

    @Value("${xiaomaigou.code.generator.comment.regex}")
    public void setCommentRegex(String commentRegex) {
        COMMENT_REGEX = commentRegex;
    }
}
