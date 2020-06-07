package ${common.packageName}.${common.moduleName}.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import ${common.packageName}.dao.entity.${table.className}Entity;
import ${common.packageName}.dao.mapper.${table.className}Mapper;
import ${common.packageName}.${common.moduleName}.dto.${table.className}DTO;
import ${common.packageName}.${common.moduleName}.service.${table.className}Service;

<#if table.hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.util.List;
import java.util.Date;

/**
 * ${table.tableComment}接口实现
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
@Service
public class ${table.className}ServiceImpl extends ServiceImpl<${table.className}Mapper, ${table.className}Entity> implements ${table.className}Service {

    private static final Logger logger = LoggerFactory.getLogger(${table.className}ServiceImpl.class);

    @Override
    public List<${table.className}Entity> listAll() {
    QueryWrapper<${table.className}Entity> ${table.classname}EntityQueryWrapper = new QueryWrapper<>();
        ${table.classname}EntityQueryWrapper.orderByDesc("${table.primaryKey.columnName}");
        return this.list(${table.classname}EntityQueryWrapper);
    }

    @Override
    public Page<${table.className}Entity> listAllPage(Integer pageNo, Integer pageSize) {
        if (null == pageNo || pageNo < 1) {
            pageNo = 1;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = 10;
        }
        QueryWrapper<${table.className}Entity> ${table.classname}EntityQueryWrapper = new QueryWrapper<>();
        ${table.classname}EntityQueryWrapper.orderByDesc("${table.primaryKey.columnName}");
        return this.page(new Page<>(pageNo, pageSize), ${table.classname}EntityQueryWrapper);
    }
				
	@Override
    public Page<${table.className}Entity> search(Integer pageNo, Integer pageSize, <#list table.columns as column><#if column.columnName!=table.primaryKey.columnName>${column.attrType} ${column.attrname}<#if column_has_next>, </#if></#if></#list>) {
        if (null == pageNo || pageNo < 1) {
            pageNo = 1;
        }
        if (null == pageSize || pageSize < 1) {
            pageSize = 10;
        }
        QueryWrapper<${table.className}Entity> ${table.classname}EntityQueryWrapper = new QueryWrapper<>();
                                    <#list table.columns as column>
                                        <#if column.columnName!=table.primaryKey.columnName>
                    ${table.classname}EntityQueryWrapper.eq(${column.attrname} != null, "${column.columnName}", ${column.attrname});
                                        </#if>
                                    </#list>
                    ${table.classname}EntityQueryWrapper.orderByDesc("${table.primaryKey.columnName}");
        return this.page(new Page<>(pageNo, pageSize), ${table.classname}EntityQueryWrapper);
    }

    @Override
    public ${table.className}Entity detail(${table.primaryKey.attrType} ${table.primaryKey.attrname}) {
        return this.getById(${table.primaryKey.attrname});
    }

    @Override
    public Boolean add(${table.className}DTO ${table.classname}DTO) {
        ${table.className}Entity ${table.classname}Entity = new ${table.className}Entity();
        BeanUtils.copyProperties(${table.classname}DTO, ${table.classname}Entity);
        ${table.classname}Entity.set${table.className}Id(IdWorker.getIdStr());
        logger.debug(String.format("新增${table.tableComment}:${table.classname}Entity=[%s]", ${table.classname}Entity.toString()));
        return this.save(${table.classname}Entity);
    }

    @Override
    public Boolean update(${table.primaryKey.attrType} ${table.primaryKey.attrname}, ${table.className}DTO ${table.classname}DTO) {
        ${table.className}Entity ${table.classname}Entity = this.getById(${table.primaryKey.attrname});
        if (null == ${table.classname}Entity) {
			logger.warn(String.format("更新不存在的${table.tableComment}:${table.primaryKey.attrname}=[%s],${table.classname}DTO=[%s]", ${table.primaryKey.attrname}, ${table.classname}DTO.toString()));
			return false;
        }
        BeanUtils.copyProperties(${table.classname}DTO, ${table.classname}Entity);
        logger.debug(String.format("更新${table.tableComment}:${table.classname}Entity=[%s]", ${table.classname}Entity.toString()));
        return this.updateById(${table.classname}Entity);
    }

    @Override
    public Boolean delete(${table.primaryKey.attrType} ${table.primaryKey.attrname}) {
        ${table.className}Entity ${table.classname}Entity = this.getById(${table.primaryKey.attrname});
        if (null == ${table.classname}Entity) {
			logger.warn(String.format("删除不存在的${table.tableComment}:${table.primaryKey.attrname}=[%s]", ${table.primaryKey.attrname}));
			return false;
        }
        return this.removeById(${table.primaryKey.attrname});
    }

    @Override
    public Boolean delete(List<${table.primaryKey.attrType}> ${table.primaryKey.attrname}List) {
        if (null == ${table.primaryKey.attrname}List || ${table.primaryKey.attrname}List.isEmpty()) {
            logger.warn("删除空集合${table.tableComment}!");
            return false;
        }
        return this.removeByIds(${table.primaryKey.attrname}List);
    }
}
