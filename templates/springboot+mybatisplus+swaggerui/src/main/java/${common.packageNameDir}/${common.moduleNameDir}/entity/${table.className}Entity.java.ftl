package ${common.packageName}.${common.moduleName}.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
<#if table.hasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.hasList>
import java.util.List;
</#if>
import java.util.Date;

/**
 * ${table.tableComment}实体类
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
@TableName("${table.tableName}")
@ApiModel(description = "${table.tableComment}实体类")
public class ${table.className}Entity implements Serializable {

    private static final Long serialVersionUID = 1L;

<#list table.columns as column>
    /**
     * ${column.columnComment}
     */
    <#if column.primaryKey>
    @TableId(value = "${column.columnName}", type = IdType.INPUT)
    <#else>
    @TableField(value = "${column.columnName}")
    </#if>
    <#if column.notNull>
    @ApiModelProperty(value = "${column.columnComment}", name = "${column.attrname}",required = true)
    <#else>
    @ApiModelProperty(value = "${column.columnComment}", name = "${column.attrname}")
    </#if>
    private ${column.attrType} ${column.attrname};
</#list>

    public ${table.className}Entity() {
    }

    public ${table.className}Entity(<#list table.columns as column>${column.attrType} ${column.attrname}<#if column_has_next>, </#if></#list>) {
<#list table.columns as column>
        this.${column.attrname} = ${column.attrname};
</#list>
    }
<#list table.columns as column>

    public ${column.attrType} get${column.attrName}() {
        return ${column.attrname};
    }

    public void set${column.attrName}(${column.attrType} ${column.attrname}) {
        this.${column.attrname} = ${column.attrname};
    }
</#list>

    @Override
    public String toString() {
        return "${table.className}Entity{" +
<#list table.columns as column>
	<#if column_index == 0>
		<#if column.attrType=='String'>
                "${column.attrname}='" + ${column.attrname} + '\'' +
		<#else>
                "${column.attrname}=" + ${column.attrname} +
		</#if>
	<#else>
		<#if column.attrType=='String'>
                ", ${column.attrname}='" + ${column.attrname} + '\'' +
		<#else>
                ", ${column.attrname}=" + ${column.attrname} +
		</#if>
	</#if>
</#list>
                '}';
    }

}
