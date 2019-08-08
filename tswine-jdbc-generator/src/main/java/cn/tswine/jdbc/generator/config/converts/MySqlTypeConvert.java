package cn.tswine.jdbc.generator.config.converts;

import cn.tswine.jdbc.generator.config.GlobalConfig;
import cn.tswine.jdbc.generator.config.ITypeConvert;
import cn.tswine.jdbc.generator.config.rules.DbColumnType;
import cn.tswine.jdbc.generator.config.rules.IColumnType;

/**
 * MySQL数据库字段类型转换
 *
 * @Author: silly
 * @Date: 2019/8/7 12:44
 * @Version 1.0
 * @Desc
 */
public class MySqlTypeConvert implements ITypeConvert {
    @Override
    public IColumnType execute(GlobalConfig globalConfig, String filedType) {
        //统一转成小写
        String ft = filedType.toLowerCase();
        switch (ft) {
            case "char":
                return DbColumnType.STRING;
            case "bigint":
                return DbColumnType.LONG;
            case "tinyint(1)":
                return DbColumnType.BOOLEAN;
            case "int":
                return DbColumnType.INTEGER;
            case "text":
                return DbColumnType.STRING;
            case "bit":
                return DbColumnType.BOOLEAN;
            case "decimal":
                return DbColumnType.BIG_DECIMAL;
            case "clob":
                return DbColumnType.CLOB;
            case "blob":
                return DbColumnType.BLOB;
            case "binary":
                return DbColumnType.BYTE_ARRAY;
            case "float":
                return DbColumnType.FLOAT;
            case "double":
                return DbColumnType.DOUBLE;
            case "json":
                return DbColumnType.STRING;
            case "enum":
                return DbColumnType.STRING;
        }
        //处理时间日期格式转换
        if (ft.contains("date") || ft.contains("time") || ft.contains("year")) {
            switch (globalConfig.getDateConvertType()) {
                case UTIL_DATE:
                    return DbColumnType.DATE;
                case SQL_DATE:
                    switch (ft) {
                        case "date":
                            return DbColumnType.DATE_SQL;
                        case "time":
                            return DbColumnType.TIME;
                        case "year":
                            return DbColumnType.DATE_SQL;
                        default:
                            return DbColumnType.TIMESTAMP;
                    }
                case TIME_DATE:
                    switch (ft) {
                        case "date":
                            return DbColumnType.LOCAL_DATE;
                        case "time":
                            return DbColumnType.LOCAL_TIME;
                        case "year":
                            return DbColumnType.YEAR;
                        default:
                            return DbColumnType.LOCAL_DATE_TIME;
                    }
            }
            
        }
        return DbColumnType.STRING;
    }
}