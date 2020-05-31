package com.xiaomaigou.code.mapper;

import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;

import java.util.List;

/**
 * 表信息
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 18:18
 */
public interface TableMapper {
    /**
     * 获取所有表
     *
     * @param tableName 表名（不为空则为搜索，否则返回全部表）
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
