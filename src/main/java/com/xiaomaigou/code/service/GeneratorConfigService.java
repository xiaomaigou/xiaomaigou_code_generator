package com.xiaomaigou.code.service;

import org.apache.commons.configuration.Configuration;

/**
 * 代码生成器配置
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:43
 */
public interface GeneratorConfigService {
    /**
     * 获取代码生成器配置
     *
     * @return 代码生成器配置
     */
    Configuration getGeneratorConfig();

}
