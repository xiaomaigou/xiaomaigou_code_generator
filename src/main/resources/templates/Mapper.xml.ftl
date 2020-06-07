<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${common.packageName}.dao.mapper.${table.className}Mapper">

    <!-- 可根据实际业务需求决定是否使用 -->
    <resultMap type="${common.packageName}.dao.entity.${table.className}Entity" id="${table.classname}EntityResultMap">
<#list table.columns as column>
        <result property="${column.attrname}" column="${column.columnName}"/>
</#list>
    </resultMap>

    <!-- 自定义SQL -->
    <select id="list${table.className}By${table.primaryKey.attrname}" resultMap="${table.classname}EntityResultMap">
        SELECT * FROM ${table.tableName}
        <trim prefix="WHERE" prefixOverrides="AND|OR">
            <if test="${table.primaryKey.attrname}!=null and ${table.primaryKey.attrname}!=''">
                <#-- #{和}在freemarker中有特殊的含义，如果想要打印 ${ 或 #{， 就要使用原生字符串，或者进行转义，为了表明字符串是原生字符串，在开始的引号或单引号之前放置字母r。-->
                AND ${table.primaryKey.columnName}=${r"#{"}${table.primaryKey.attrname}${r"}"}
            </if>
        </trim>
        ORDER BY ${table.primaryKey.columnName} DESC
    </select>

</mapper>