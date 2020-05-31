package com.xiaomaigou.code.service;

import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;

import java.util.List;

/**
 * 表信息
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 17:53
 */
public interface TableService {

    /**
     * 获取所有表
     *
     * @param tableName 表名（搜索）
     * @return 所有表
     */
    List<TableEntity> listTable(String tableName);

    /**
     * 根据表名查询表详细信息
     *
     * @param tableName 表名
     * @return 表详细信息
     */
    TableEntity findTableByTableName(String tableName);

    /**
     * 根据表名查询列详细信息
     *
     * @param tableName 表名
     * @return 列详细信息
     */
    List<ColumnEntity> findColumnsByTableName(String tableName);

}
