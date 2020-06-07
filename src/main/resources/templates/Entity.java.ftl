package ${common.packageName}.dao.entity;

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
    <#if column.columnName==table.primaryKey.columnName>
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

}
