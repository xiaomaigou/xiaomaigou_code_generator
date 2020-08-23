package com.xiaomaigou.code.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DatabaseDriver;
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
     * 数据库类型，由spring.datasource.url自动推断而来，可选类型:[mysql、oracle、sqlserver、postgresql]
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
    public static String FREEMARKER_SUFFIX = ".ftl";

    /**
     * freemarker模板文件加载目录
     * spring.freemarker.template-loader-path
     */
    public static String FREEMARKER_TEMPLATE_LOADER_PATH = "classpath:/templates/";

    /**
     * 从备注中转换为表名或者字段名称的分隔符，比如";"
     * xiaomaigou.code.generator.comment.regex
     */
    public static String COMMENT_REGEX;

    @Value("${spring.datasource.url}")
    public void setDatasourceType(String datasourceUrl) {
        DatabaseDriver databaseDriver = DatabaseDriver.fromJdbcUrl(datasourceUrl);
        DATASOURCE_TYPE = databaseDriver.getId();
    }

    @Value("${xiaomaigou.code.generator.properties.config_path}")
    public void setPropertiesConfigPath(String propertiesConfigPath) {
        PROPERTIES_CONFIG_PATH = propertiesConfigPath;
    }

    @Value("${spring.freemarker.suffix}")
    public void setFreemarkerSuffix(String freemarkerSuffix) {
        FREEMARKER_SUFFIX = freemarkerSuffix;
    }

    @Value("${spring.freemarker.template-loader-path}")
    public void setFreemarkerTemplateLoaderPath(String freemarkerTemplateLoaderPath) {
        FREEMARKER_TEMPLATE_LOADER_PATH = freemarkerTemplateLoaderPath;
    }

    @Value("${xiaomaigou.code.generator.comment.regex}")
    public void setCommentRegex(String commentRegex) {
        COMMENT_REGEX = commentRegex;
    }
}
