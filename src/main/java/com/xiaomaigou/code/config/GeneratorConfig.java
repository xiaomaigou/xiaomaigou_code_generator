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

    @Value("${xiaomaigou.code.generator.datasource.type}")
    public void setDatasourceType(String datasourceType) {
        DATASOURCE_TYPE = datasourceType;
    }

    @Value("${xiaomaigou.code.generator.properties.config_path}")
    public void setPropertiesConfigPath(String propertiesConfigPath) {
        PROPERTIES_CONFIG_PATH = propertiesConfigPath;
    }
}
