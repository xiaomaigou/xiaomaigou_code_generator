package ${common.packageName}.${common.moduleName}.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
<#if table.hasBigDecimal>
import java.math.BigDecimal;
</#if>
<#if table.hasList>
import java.util.List;
</#if>
import java.util.Date;

/**
 * 新增/修改${table.tableComment}传输对象
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
@ApiModel(description = "新增/修改${table.tableComment}传输对象")
public class ${table.className}DTO implements Serializable {

    private static final Long serialVersionUID = 1L;

<#list table.columns as column>
    /**
     * ${column.columnComment}
     */
    <#if column.primaryKey>
//    @ApiModelProperty(value = "${column.columnComment}", name = "${column.attrname}",required = true)
//    private ${column.attrType} ${column.attrname};
    <#else>
        <#if column.notNull>
    @ApiModelProperty(value = "${column.columnComment}", name = "${column.attrname}",required = true)
            <#if column.attrType=='String'>
	@NotBlank(message = "${column.columnComment}不能为空!")
	        <#else>
	@NotNull(message = "${column.columnComment}不能为空!")
            </#if>
        <#else>
    @ApiModelProperty(value = "${column.columnComment}", name = "${column.attrname}")
        </#if>
    private ${column.attrType} ${column.attrname};
    </#if>
</#list>

}
