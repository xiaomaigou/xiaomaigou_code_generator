package ${common.packageName}.${common.moduleName}.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import ${common.packageName}.${common.moduleName}.entity.${table.className}Entity;
import ${common.packageName}.${common.moduleName}.dto.${table.className}DTO;

<#if table.hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.util.List;
import java.util.Date;

/**
 * ${table.tableComment}接口
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
public interface ${table.className}Service extends IService<${table.className}Entity> {

    /**
     * 获取所有${table.tableComment}
     *
     * @return 所有${table.tableComment}
     */
    List<${table.className}Entity> listAll();

    /**
     * 获取${table.tableComment}(分页)
     *
     * @param pageNo   当前页，默认1
     * @param pageSize 每页显示条数，默认10
     * @return ${table.tableComment}
     */
    Page<${table.className}Entity> listAllPage(Integer pageNo, Integer pageSize);
	
	/**
     * 搜索${table.tableComment}(分页)
     *
     * @param pageNo              当前页，默认1
     * @param pageSize            每页显示条数，默认10
            <#list table.columns as column>
                <#if column.search>
     * @param ${column.attrname} ${column.columnComment}
                </#if>
            </#list>
     * @return ${table.tableComment}
     */
    Page<${table.className}Entity> search(Integer pageNo, Integer pageSize, <#list table.columns as column><#if column.search>${column.attrType} ${column.attrname}<#if column_has_next>, </#if></#if></#list>);

    /**
     * 根据${table.tableComment}ID获取${table.tableComment}详情
     *
     * @param ${table.primaryKey.attrname} ${table.tableComment}ID
     * @return ${table.tableComment}详情
     */
    ${table.className}Entity detail(${table.primaryKey.attrType} ${table.primaryKey.attrname});

    /**
     * 新增${table.tableComment}
     *
     * @param ${table.classname}DTO ${table.tableComment}信息
     * @return 执行结果
     */
    Boolean add(${table.className}DTO ${table.classname}DTO);

    /**
     * 根据${table.tableComment}ID更新${table.tableComment}
     *
     * @param ${table.primaryKey.attrname}  ${table.tableComment}ID
     * @param ${table.classname}DTO ${table.tableComment}信息
     * @return 执行结果
     */
    Boolean update(${table.primaryKey.attrType} ${table.primaryKey.attrname}, ${table.className}DTO ${table.classname}DTO);

    /**
     * 根据${table.tableComment}ID删除${table.tableComment}
     *
     * @param ${table.primaryKey.attrname} ${table.tableComment}ID
     * @return 执行结果
     */
    Boolean delete(${table.primaryKey.attrType} ${table.primaryKey.attrname});

    /**
     * 根据${table.tableComment}ID删除${table.tableComment}(多条记录)
     *
     * @param ${table.primaryKey.attrname}List ${table.tableComment}ID List
     * @return 执行结果
     */
    Boolean delete(List<${table.primaryKey.attrType}> ${table.primaryKey.attrname}List);

}
