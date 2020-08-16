package com.xiaomaigou.code.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaomaigou.code.dto.Result;
import com.xiaomaigou.code.entity.TableEntity;
import com.xiaomaigou.code.service.TableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 表信息
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 17:48
 */
@Api(tags = "表信息", value = "表信息")
@RestController
@RequestMapping("codeGenerator/table")
public class TableController {

    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private TableService tableService;

    @ApiOperation(value = "搜索表(分页)", notes = "搜索表(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页，默认1", paramType = "query", required = false, dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数，默认10", paramType = "query", required = false, dataType = "int", defaultValue = "10"),
            @ApiImplicitParam(name = "tableName", value = "表名", paramType = "query", required = false, dataType = "String")
    })
    @GetMapping("listTable")
    public Result<Page<TableEntity>> listTable(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                               @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                               @RequestParam(value = "tableName", required = false) String tableName) {
        Page<TableEntity> tableEntityList = tableService.listTable(pageNo, pageSize, tableName);
        return new Result<Page<TableEntity>>().success(tableEntityList);
    }

}
