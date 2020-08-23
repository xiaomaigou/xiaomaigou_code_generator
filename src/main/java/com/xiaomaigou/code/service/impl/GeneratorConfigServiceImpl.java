package com.xiaomaigou.code.service.impl;

import com.xiaomaigou.code.config.GeneratorConfig;
import com.xiaomaigou.code.service.GeneratorConfigService;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:51
 */
@Service
public class GeneratorConfigServiceImpl implements GeneratorConfigService {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorConfigServiceImpl.class);

    @Override
    public Configuration getGeneratorConfig() {
        try {
            return new PropertiesConfiguration(GeneratorConfig.PROPERTIES_CONFIG_PATH);
        } catch (ConfigurationException e) {
            logger.error("获取配置信息失败!", e);
            throw new RuntimeException("获取配置信息失败!", e);
        }
    }
}
