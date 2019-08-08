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
        String ft = fieldType.toLowerCase();
        switch (ft) {
            case "char":
                return DbColumnType.STRING;
            case "bigint":
                return DbColumnType.LONG;
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
                return DbColumnType.BYTE_ARRAY;
            case "float":
                return DbColumnType.FLOAT;
            case "double":
                return DbColumnType.DOUBLE;
            case "json":
                return DbColumnType.STRING;
            case "enum":
                return DbColumnType.STRING;
            case "boolean":
                return DbColumnType.BOOLEAN;
        }
        //处理时间日期格式转换
        if (ft.contains("date") || ft.contains("time")) {
            switch (globalConfig.getDateConvertType()) {
                case UTIL_DATE:
                    return DbColumnType.DATE;
                case SQL_DATE:
                    switch (ft) {
                        case "date":
                            return DbColumnType.DATE_SQL;
                        case "time":
                            return DbColumnType.TIME;
                        default:
                            return DbColumnType.TIMESTAMP;
                    }
                case TIME_DATE:
                    switch (ft) {
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
        }
        return DbColumnType.STRING;
    }


}
