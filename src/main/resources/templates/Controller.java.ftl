package ${common.packageName}.${common.moduleName}.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ${common.packageName}.common.dto.Result;
import ${common.packageName}.dao.entity.${table.className}Entity;
import ${common.packageName}.${common.moduleName}.dto.${table.className}DTO;
import ${common.packageName}.${common.moduleName}.service.${table.className}Service;

import java.util.Arrays;
<#if table.hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.util.List;
import java.util.Date;

/**
 * ${table.tableComment}
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
@Api(tags = "${table.tableComment}", description = "${table.tableComment}相关接口")
@RestController
@RequestMapping("${common.moduleName}/${table.pathName}")
public class ${table.className}Controller {

    private static final Logger logger = LoggerFactory.getLogger(${table.className}Controller.class);

    @Autowired
    private ${table.className}Service ${table.classname}Service;

    @ApiOperation(value = "所有${table.tableComment}", notes = "获取所有${table.tableComment}")
    @GetMapping("listAll")
    public Result<List<${table.className}Entity>> listAll() {
    Result<List<${table.className}Entity>> result = new Result<>();
        try {
			List<${table.className}Entity> ${table.classname}EntityList = ${table.classname}Service.listAll();
            return result.success(${table.classname}EntityList);
        } catch (Exception e) {
            logger.error("获取${table.tableComment}失败!", e);
            return result.fail("获取${table.tableComment}失败，请稍后重试!");
        }
    }

    @ApiOperation(value = "${table.tableComment}(分页)", notes = "获取${table.tableComment}(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页，默认1", paramType = "query", required = false, dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数，默认10", paramType = "query", required = false, dataType = "int", defaultValue = "10")
    })
    @GetMapping("listAllPage")
    public Result<Page<${table.className}Entity>> listAllPage(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo, @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {

        Result<Page<${table.className}Entity>> result = new Result<>();
        try {
            Page<${table.className}Entity> ${table.classname}EntityPage = ${table.classname}Service.listAllPage(pageNo, pageSize);
            return result.success(${table.classname}EntityPage);
        } catch (Exception e) {
            logger.error(String.format("获取${table.tableComment}失败!pageNo=[%s],pageSize=[%s]", pageNo, pageSize), e);
            return result.fail("获取${table.tableComment}失败，请稍后重试!");
        }
    }

	@ApiOperation(value = "搜索${table.tableComment}(分页)", notes = "搜索${table.tableComment}(分页)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页，默认1", paramType = "query", required = false, dataType = "int", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数，默认10", paramType = "query", required = false, dataType = "int", defaultValue = "10"),
<#list table.columns as column>
    <#if column.columnName!=table.primaryKey.columnName>
			@ApiImplicitParam(name = "${column.attrname}", value = "${column.columnComment}", paramType = "query", required = false, dataType = "${column.attrType}")<#if column_has_next>,<#else >})</#if>
    </#if>
</#list>
    @GetMapping("search")
    public Result<Page<${table.className}Entity>> search(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                        <#list table.columns as column>
                            <#if column.columnName!=table.primaryKey.columnName>
                                                      @RequestParam(value = "${column.attrname}", required = false) ${column.attrType} ${column.attrname}<#if column_has_next>,<#else >) {</#if>
                            </#if>
                        </#list>

        Result<Page<${table.className}Entity>> result = new Result<>();
        try {
            Page<${table.className}Entity> ${table.classname}EntityPage = ${table.classname}Service.search(pageNo, pageSize,<#list table.columns as column><#if column.columnName!=table.primaryKey.columnName>${column.attrname}<#if column_has_next>, </#if></#if></#list>);
            return result.success(${table.classname}EntityPage);
        } catch (Exception e) {
            logger.error(String.format("搜索${table.tableComment}失败!<#list table.columns as column><#if column.columnName!=table.primaryKey.columnName>${column.attrname}=[%s]<#if column_has_next>,<#else >",</#if></#if></#list><#list table.columns as column><#if column.columnName!=table.primaryKey.columnName>${column.attrname}<#if column_has_next>, </#if></#if></#list>), e);
            return result.fail("搜索${table.tableComment}失败，请稍后重试!");
        }
    }

    @ApiOperation(value = "${table.tableComment}详情", notes = "根据${table.tableComment}ID获取${table.tableComment}详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "${table.primaryKey.attrname}", value = "${table.tableComment}ID", paramType = "path", required = true, dataType = "${table.primaryKey.attrType}")
    })
    @GetMapping("detail/{${table.primaryKey.attrname}}")
    public Result<${table.className}Entity> detail(@PathVariable(value = "${table.primaryKey.attrname}", required = true) ${table.primaryKey.attrType} ${table.primaryKey.attrname}) {
        Result<${table.className}Entity> result = new Result<>();
        try {
            ${table.className}Entity ${table.classname}Entity = ${table.classname}Service.detail(${table.primaryKey.attrname});
            if (${table.classname}Entity == null) {
                return result.notFound("查询的${table.tableComment}不存在!");
            } else {
                return result.success(${table.classname}Entity);
            }
        } catch (Exception e) {
            logger.error(String.format("获取${table.tableComment}详情失败!${table.primaryKey.attrname}=[%s]", ${table.primaryKey.attrname}), e);
            return result.fail("获取${table.tableComment}详情失败，请稍后重试!");
        }
    }

    @ApiOperation(value = "新增${table.tableComment}", notes = "新增${table.tableComment}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "${table.classname}DTO", value = "${table.tableComment}信息", paramType = "body", required = true, dataType = "${table.className}DTO")
    })
    @PostMapping("add")
    public Result add(@RequestBody ${table.className}DTO ${table.classname}DTO) {
        Result result = new Result<>();
        try {
            Boolean success = ${table.classname}Service.add(${table.classname}DTO);
            if (success) {
                return result.success("新增${table.tableComment}成功!");
            } else {
                logger.warn(String.format("新增${table.tableComment}失败!${table.classname}DTO=[%s]", ${table.classname}DTO.toString()));
                return result.fail("新增${table.tableComment}失败，请稍后重试!");
            }
        } catch (Exception e) {
            logger.error(String.format("新增${table.tableComment}失败!${table.classname}DTO=[%s]", ${table.classname}DTO.toString()), e);
            return result.fail("新增${table.tableComment}失败，请稍后重试!");
        }
    }

    @ApiOperation(value = "更新${table.tableComment}", notes = "根据${table.tableComment}ID更新${table.tableComment}详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "${table.primaryKey.attrname}", value = "${table.tableComment}ID", paramType = "path", required = true, dataType = "${table.primaryKey.attrType}"),
            @ApiImplicitParam(name = "${table.classname}DTO", value = "${table.tableComment}信息", paramType = "body", required = true, dataType = "${table.className}DTO")
    })
    @PutMapping("update/{${table.primaryKey.attrname}}")
    public Result update(@PathVariable(value = "${table.primaryKey.attrname}", required = true) ${table.primaryKey.attrType} ${table.primaryKey.attrname}, @RequestBody ${table.className}DTO ${table.classname}DTO) {
        Result result = new Result<>();
        try {
            Boolean success = ${table.classname}Service.update(${table.primaryKey.attrname}, ${table.classname}DTO);
            if (success) {
                return result.success("更新${table.tableComment}成功!");
            } else {
                logger.warn(String.format("更新${table.tableComment}失败!${table.primaryKey.attrname}=[%s],${table.classname}DTO=[%s]", ${table.primaryKey.attrname}, ${table.classname}DTO.toString()));
                return result.fail("更新${table.tableComment}失败，请稍后重试!");
            }
        } catch (Exception e) {
            logger.error(String.format("更新${table.tableComment}失败!${table.primaryKey.attrname}=[%s],${table.classname}DTO=[%s]", ${table.primaryKey.attrname}, ${table.classname}DTO.toString()), e);
            return result.fail("更新${table.tableComment}失败，请稍后重试!");
        }
    }

    @ApiOperation(value = "删除${table.tableComment}", notes = "根据${table.tableComment}ID删除${table.tableComment}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "${table.primaryKey.attrname}", value = "${table.tableComment}ID", paramType = "path", required = true, dataType = "${table.primaryKey.attrType}")
    })
    @DeleteMapping("delete/{${table.primaryKey.attrname}}")
    public Result delete(@PathVariable(value = "${table.primaryKey.attrname}", required = true) ${table.primaryKey.attrType} ${table.primaryKey.attrname}) {
        Result result = new Result<>();
        try {
            Boolean success = ${table.classname}Service.delete(${table.primaryKey.attrname});
            if (success) {
                return result.success("删除${table.tableComment}成功!");
            } else {
                logger.warn(String.format("删除${table.tableComment}失败!${table.primaryKey.attrname}=[%s]", ${table.primaryKey.attrname}));
                return result.fail("删除${table.tableComment}失败，请稍后重试!");
            }
        } catch (Exception e) {
            logger.error(String.format("删除${table.tableComment}失败!${table.primaryKey.attrname}=[%s]", ${table.primaryKey.attrname}), e);
            return result.fail("删除${table.tableComment}失败，请稍后重试!");
        }
    }

    @ApiOperation(value = "删除${table.tableComment}(多条记录)", notes = "根据${table.tableComment}ID删除${table.tableComment}(多条记录)")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "${table.primaryKey.attrname}s", value = "${table.tableComment}ID(多个ID之间使用逗号\",\"分隔)", paramType = "query", required = true, dataType = "${table.primaryKey.attrType}")
    })
    @DeleteMapping("delete")
    public Result deletes(@RequestParam(value = "${table.primaryKey.attrname}s", required = true) ${table.primaryKey.attrType} ${table.primaryKey.attrname}s) {
        Result result = new Result<>();

        List<${table.primaryKey.attrType}> ${table.primaryKey.attrname}List = Arrays.asList(${table.primaryKey.attrname}s.split(","));
        if (${table.primaryKey.attrname}List.isEmpty()) {
            return result.badRequest("请至少选择一条记录!");
        }
        try {
            Boolean success = ${table.classname}Service.delete(${table.primaryKey.attrname}List);
            if (success) {
                return result.success("删除${table.tableComment}成功!");
            } else {
                logger.warn(String.format("删除${table.tableComment}失败!${table.primaryKey.attrname}List=[%s]", ${table.primaryKey.attrname}List.toString()));
                return result.fail("删除${table.tableComment}失败，请稍后重试!");
            }
        } catch (Exception e) {
            logger.error(String.format("删除${table.tableComment}失败!${table.primaryKey.attrname}List=[%s]", ${table.primaryKey.attrname}List.toString()), e);
            return result.fail("删除${table.tableComment}失败，请稍后重试!");
        }
    }
}
