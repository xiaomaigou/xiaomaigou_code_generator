package com.xiaomaigou.code.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;
import org.apache.ibatis.annotations.Param;

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
     * 获取所有表(分页)
     *
     * @param page      分页
     * @param tableName 表名（不为空则为搜索，否则返回全部表）
     * @return 所有表
     */
    Page<TableEntity> listTable(Page page, @Param("tableName") String tableName);

    /**
     * 根据表名查询表详细信息
     *
     * @param tableName 表名
     * @return 表详细信息
     */
    TableEntity findTableByTableName(@Param("tableName") String tableName);

    /**
     * 根据表名查询列详细信息
     *
     * @param tableName 表名
     * @return 列详细信息
     */
    List<ColumnEntity> findColumnsByTableName(@Param("tableName") String tableName);

}
