package com.xiaomaigou.code.service;

import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;
import org.apache.commons.configuration.Configuration;

import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * 生成代码
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 17:53
 */
public interface GeneratorService {

    /**
     * 获取代码生成器配置
     *
     * @return 代码生成器配置
     */
    Configuration getGeneratorConfig();

    /**
     * 获取模板
     *
     * @return 模板
     */
    List<String> getTemplates();

    /**
     * 生成代码
     *
     * @param tableNameList 表名List
     * @return 生成结果
     */
    byte[] generatorCode(List<String> tableNameList);

    /**
     * 生成代码
     *
     * @param tableEntity      表详细信息
     * @param columnEntityList 列详细信息列表
     * @param zipOutputStream  输出流
     */
    void generatorCode(TableEntity tableEntity, List<ColumnEntity> columnEntityList, ZipOutputStream zipOutputStream);

}
