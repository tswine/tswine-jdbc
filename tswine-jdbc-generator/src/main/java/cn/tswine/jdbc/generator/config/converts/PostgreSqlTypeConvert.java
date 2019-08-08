package cn.tswine.jdbc.generator.config.converts;

import cn.tswine.jdbc.generator.config.GlobalConfig;
import cn.tswine.jdbc.generator.config.ITypeConvert;
import cn.tswine.jdbc.generator.config.rules.DbColumnType;
import cn.tswine.jdbc.generator.config.rules.IColumnType;

/**
 * PostgreSQL字段类型转换
 *
 * @Author: silly
 * @Date: 2019/8/7 13:02
 * @Version 1.0
 * @Desc
 */
public class PostgreSqlTypeConvert implements ITypeConvert {

    @Override
    public IColumnType execute(GlobalConfig globalConfig, String fieldType) {
        String t = fieldType.toLowerCase();
        if (t.contains("char")) {
            return DbColumnType.STRING;
        } else if (t.contains("bigint")) {
            return DbColumnType.LONG;
        } else if (t.contains("int")) {
            return DbColumnType.INTEGER;
        } else if (t.contains("date") || t.contains("time")) {
            switch (globalConfig.getDateConvertType()) {
                case UTIL_DATE:
                    return DbColumnType.DATE;
                case SQL_DATE:
                    switch (t) {
                        case "date":
                            return DbColumnType.DATE_SQL;
                        case "time":
                            return DbColumnType.TIME;
                        default:
                            return DbColumnType.TIMESTAMP;
                    }
                case TIME_DATE:
                    switch (t) {
                        case "date":
                            return DbColumnType.LOCAL_DATE;
                        case "time":
                            return DbColumnType.LOCAL_TIME;
                        default:
                            return DbColumnType.LOCAL_DATE_TIME;
                    }
                default:
                    return DbColumnType.DATE;
            }
        } else if (t.contains("text")) {
            return DbColumnType.STRING;
        } else if (t.contains("bit")) {
            return DbColumnType.BOOLEAN;
        } else if (t.contains("decimal")) {
            return DbColumnType.BIG_DECIMAL;
        } else if (t.contains("clob")) {
            return DbColumnType.CLOB;
        } else if (t.contains("blob")) {
            return DbColumnType.BYTE_ARRAY;
        } else if (t.contains("float")) {
            return DbColumnType.FLOAT;
        } else if (t.contains("double")) {
            return DbColumnType.DOUBLE;
        } else if (t.contains("json") || t.contains("enum")) {
            return DbColumnType.STRING;
        } else if (t.contains("boolean")) {
            return DbColumnType.BOOLEAN;
        }
        return DbColumnType.STRING;
    }


}
