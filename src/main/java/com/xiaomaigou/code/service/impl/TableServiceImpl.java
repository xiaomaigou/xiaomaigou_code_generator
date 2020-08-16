package com.xiaomaigou.code.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomaigou.code.entity.ColumnEntity;
import com.xiaomaigou.code.entity.TableEntity;
import com.xiaomaigou.code.mapper.TableMapper;
import com.xiaomaigou.code.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(TableServiceImpl.class);

    @Autowired
    private TableMapper tableMapper;

    @Override
    public Page<TableEntity> listTable(Integer pageNo, Integer pageSize, String tableName) {
        if (null == pageNo || pageNo < 1) {
            pageNo = 1;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = 10;
        }
        return tableMapper.listTable(new Page(pageNo, pageSize), tableName);
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
