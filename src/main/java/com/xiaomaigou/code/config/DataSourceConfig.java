package com.xiaomaigou.code.config;

import com.xiaomaigou.code.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${xiaomaigou.code.generator.datasource.type}")
    private String dataSourceType;

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
        if ("mysql".equalsIgnoreCase(dataSourceType)) {
            return mySQLTableMapper;
        } else if ("oracle".equalsIgnoreCase(dataSourceType)) {
            return oracleTableMapper;
        } else if ("sqlserver".equalsIgnoreCase(dataSourceType)) {
            return sqlServerTableMapper;
        } else if ("postgresql".equalsIgnoreCase(dataSourceType)) {
            return postgreSQLTableMapper;
        } else {
            throw new RuntimeException("不支持当前数据库类型：" + dataSourceType);
        }
    }
}
