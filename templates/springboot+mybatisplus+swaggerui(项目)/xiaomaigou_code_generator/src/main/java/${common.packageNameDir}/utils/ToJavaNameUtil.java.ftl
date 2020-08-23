package ${common.packageName}.utils;

import org.apache.commons.lang.WordUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Description
 *
 * @author ${common.author}
 * @version ${common.version}
 * @date ${common.dateTime}
 */
public class ToJavaNameUtil {

    /**
     * 表名转换为Java类名(第一个字母大写)，如：sys_user => SysUser
     *
     * @param tableName        表名
     * @param tablePrefixArray 表前缀
     * @return Java类名(第一个字母大写)
     */
    public static String tableNameToJavaClassName(String tableName, String[] tablePrefixArray) {
        // 首先去掉表前缀
        if (ArrayUtils.isNotEmpty(tablePrefixArray)) {
            for (String tablePrefix : tablePrefixArray) {
                tableName = tableName.replace(tablePrefix, StringUtils.EMPTY);
            }
        }
        // 驼峰命名转换
        return WordUtils.capitalizeFully(tableName, new char[]{'_'}).replace("_", StringUtils.EMPTY);
    }

    /**
     * 字段名转换为Java属性名(第一个字母大写),如：user_name => UserName
     *
     * @param columnName 字段名
     * @return Java属性名(第一个字母大写)
     */
    public static String columnNameToJavaAttrName(String columnName) {
        // 驼峰命名转换
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", StringUtils.EMPTY);
    }

    /**
     * 转换为Java属性名(第一个字母小写)，如：UserName => userName
     *
     * @param name 名称
     * @return Java属性名(第一个字母小写)
     */
    public static String nameToJavaAttrName(String name) {
        // 属性名称(第一个字母小写)
        return StringUtils.uncapitalize(name);
    }

}
