package com.xiaomaigou.code.controller;

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

import java.util.List;

/**
 * 表信息
 *
 * @author xiaomaiyun
 * @version 1.2.3
 * @date 2020/5/30 17:48
 */
@Api(tags = "表信息", description = "表信息")
@RestController
@RequestMapping("codeGenerator/table")
public class TableController {

    private static final Logger logger = LoggerFactory.getLogger(TableController.class);

    @Autowired
    private TableService tableService;

    @ApiOperation(value = "获取所有表", notes = "获取所有表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableName", value = "表名", paramType = "query", required = false, dataType = "String")
    })
    @GetMapping("listTable")
    public Result<List<TableEntity>> listTable(@RequestParam(value = "tableName", required = false) String tableName) {
        List<TableEntity> tableEntityList = tableService.listTable(tableName);
        return new Result<List<TableEntity>>().success(tableEntityList);
    }

}
