package ${common.packageName}.${common.moduleName}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import ${common.packageName}.${common.moduleName}.entity.${table.className}Entity;

import java.util.List;

/**
 * ${table.tableComment}
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
@Mapper
public interface ${table.className}Mapper extends BaseMapper<${table.className}Entity> {

    /**
     * 根据${table.tableComment}ID查询${table.tableComment}
     *
     * @param ${table.primaryKey.attrname} ${table.tableComment}ID
     * @return ${table.tableComment}列表
     */
    List<${table.className}Entity> list${table.className}By${table.primaryKey.attrname}(${table.primaryKey.attrType} ${table.primaryKey.attrname});

}
