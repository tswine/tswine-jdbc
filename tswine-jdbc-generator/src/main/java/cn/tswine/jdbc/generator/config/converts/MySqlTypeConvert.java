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
        String t = filedType.toLowerCase();
        if (t.contains("char")) {
            return DbColumnType.STRING;
        } else if (t.contains("bigint")) {
            return DbColumnType.LONG;
        } else if (t.contains("tinyint(1)")) {
            return DbColumnType.BOOLEAN;
        } else if (t.contains("int")) {
            return DbColumnType.INTEGER;
        } else if (t.contains("text")) {
            return DbColumnType.STRING;
        } else if (t.contains("bit")) {
            return DbColumnType.BOOLEAN;
        } else if (t.contains("decimal")) {
            return DbColumnType.BIG_DECIMAL;
        } else if (t.contains("clob")) {
            return DbColumnType.CLOB;
        } else if (t.contains("blob")) {
            return DbColumnType.BLOB;
        } else if (t.contains("binary")) {
            return DbColumnType.BYTE_ARRAY;
        } else if (t.contains("float")) {
            return DbColumnType.FLOAT;
        } else if (t.contains("double")) {
            return DbColumnType.DOUBLE;
        } else if (t.contains("json") || t.contains("enum")) {
            return DbColumnType.STRING;
        } else if (t.contains("date") || t.contains("time") || t.contains("year")) {
            switch (globalConfig.getDateConvertType()) {
                case UTIL_DATE:
                    return DbColumnType.DATE;
                case SQL_DATE:
                    switch (t) {
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
                    switch (t) {
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