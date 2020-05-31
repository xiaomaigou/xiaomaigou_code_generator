package com.xiaomaigou.code.service.impl;

import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;
import com.xiaomaigou.code.mapper.TableMapper;
import com.xiaomaigou.code.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 18:01
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableMapper tableMapper;

    @Override
    public List<TableEntity> listTable(String tableName) {
        return tableMapper.listTable(tableName);
    }

    @Override
    public TableEntity findTableByTableName(String tableName) {
        return tableMapper.findTableByTableName(tableName);
    }

    @Override
    public List<ColumnEntity> findColumnsByTableName(String tableName) {
        return tableMapper.findColumnsByTableName(tableName);
    }
}
