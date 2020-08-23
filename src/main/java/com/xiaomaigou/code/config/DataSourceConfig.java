package com.xiaomaigou.code.config;

import com.xiaomaigou.code.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据源配置
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 12:13
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private MySQLTableMapper mySQLTableMapper;

    @Autowired
    private OracleTableMapper oracleTableMapper;

    @Autowired
    private SQLServerTableMapper sqlServerTableMapper;

    @Autowired
    private PostgreSQLTableMapper postgreSQLTableMapper;

    @Bean
    @Primary
    public TableMapper getTableMapper() {
        // 根据数据源配置URL自动推断数据库类型
        if (StringUtils.equalsIgnoreCase(DatabaseDriver.MYSQL.getId(), GeneratorConfig.DATASOURCE_TYPE)) {
            return mySQLTableMapper;
        } else if (StringUtils.equalsIgnoreCase(DatabaseDriver.ORACLE.getId(), GeneratorConfig.DATASOURCE_TYPE)) {
            return oracleTableMapper;
        } else if (StringUtils.equalsIgnoreCase(DatabaseDriver.SQLSERVER.getId(), GeneratorConfig.DATASOURCE_TYPE)) {
            return sqlServerTableMapper;
        } else if (StringUtils.equalsIgnoreCase(DatabaseDriver.POSTGRESQL.getId(), GeneratorConfig.DATASOURCE_TYPE)) {
            return postgreSQLTableMapper;
        } else {
            throw new RuntimeException(String.format("不支持当前数据库类型dataSourceType=[%s]，可选数据库类型:[%s、%s、%s、%s]", GeneratorConfig.DATASOURCE_TYPE, DatabaseDriver.MYSQL.getId(), DatabaseDriver.ORACLE.getId(), DatabaseDriver.SQLSERVER.getId(), DatabaseDriver.POSTGRESQL.getId()));
        }
    }
}
