package com.xiaomaigou.code.config;

import com.xiaomaigou.code.mapper.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Autowired
    private MySQLTableMapper mySQLTableMapper;

    @Autowired
    private OracleTableMapper oracleTableMapper;

    @Autowired
    private SQLServerTableMapper sqlServerTableMapper;

    @Autowired
    private PostgreSQLTableMapper postgreSQLTableMapper;

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Bean
    @Primary
    public TableMapper getTableMapper() {
        String dataSourceType = DatabaseDriver.fromJdbcUrl(dataSourceUrl).getId();
        logger.info(String.format("执行getTableMapper()，dataSourceType=[%s]", dataSourceType));
        // 根据数据源配置URL自动推断数据库类型
        if (StringUtils.equalsIgnoreCase(DatabaseDriver.MYSQL.getId(), dataSourceType)) {
            return mySQLTableMapper;
        } else if (StringUtils.equalsIgnoreCase(DatabaseDriver.ORACLE.getId(), dataSourceType)) {
            return oracleTableMapper;
        } else if (StringUtils.equalsIgnoreCase(DatabaseDriver.SQLSERVER.getId(), dataSourceType)) {
            return sqlServerTableMapper;
        } else if (StringUtils.equalsIgnoreCase(DatabaseDriver.POSTGRESQL.getId(), dataSourceType)) {
            return postgreSQLTableMapper;
        } else {
            throw new RuntimeException(String.format("不支持当前数据库类型dataSourceType=[%s]，可选数据库类型:[%s、%s、%s、%s]", dataSourceType, DatabaseDriver.MYSQL.getId(), DatabaseDriver.ORACLE.getId(), DatabaseDriver.SQLSERVER.getId(), DatabaseDriver.POSTGRESQL.getId()));
        }
    }
}
