package com.xiaomaigou.code.service;

import com.xiaomaigou.code.dto.GenerateCodeTemplateDataDTO;
import com.xiaomaigou.code.dto.TemplateData;
import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;

import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * 生成代码
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/8/16 18:41
 */
public interface GenerateCodeService {
    /**
     * 生成代码
     *
     * @param tableNameList 表名List
     * @return 生成结果
     */
    byte[] generateCode(List<String> tableNameList);

    /**
     * 通过生成代码模板数据生成代码
     *
     * @param generateCodeTemplateDataDTO 生成代码模板数据
     * @return 生成结果
     */
    byte[] generateCode(GenerateCodeTemplateDataDTO generateCodeTemplateDataDTO);

    /**
     * 生成代码
     *
     * @param templateData    模板数据
     * @param zipOutputStream 输出流
     */
    void generateCode(TemplateData templateData, ZipOutputStream zipOutputStream);

    /**
     * 生成代码
     *
     * @param tableEntity      表详细信息
     * @param columnEntityList 列详细信息列表
     * @param zipOutputStream  输出流
     */
    void generateCode(TableEntity tableEntity, List<ColumnEntity> columnEntityList, ZipOutputStream zipOutputStream);
}
