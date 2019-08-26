package cn.tswine.jdbc.common.rules;

import cn.tswine.jdbc.common.rules.convert.object.LocalDateObjectConvert;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.*;
import java.time.*;

/**
 * 表字段类型
 *
 * @Author: silly
 * @Date: 2019/8/7 12:40
 * @Version 1.0
 * @Desc
 */
public enum FieldType implements IFieldType {

    // 基本类型
    BASE_BYTE("byte", byte.class, null, null),
    BASE_SHORT("short", short.class, null, null),
    BASE_CHAR("char", char.class, null, null),
    BASE_INT("int", int.class, null, null),
    BASE_LONG("long", long.class, null, null),
    BASE_FLOAT("float", float.class, null, null),
    BASE_DOUBLE("double", double.class, null, null),
    BASE_BOOLEAN("boolean", boolean.class, null, null),

    // 包装类型
    BYTE("Byte", Byte.class, null, null),
    SHORT("Short", Short.class, null, null),
    CHARACTER("Character", Character.class, null, null),
    INTEGER("Integer", Integer.class, null, null),
    LONG("Long", Long.class, null, null),
    FLOAT("Float", Float.class, null, null),
    DOUBLE("Double", Double.class, null, null),
    BOOLEAN("Boolean", Boolean.class, null, null),
    STRING("String", String.class, null, null),

    // sql 包下数据类型
    DATE_SQL("Date", Date.class, "java.sql.Date", null),
    TIME("Time", Time.class, "java.sql.Time", null),
    TIMESTAMP("Timestamp", Timestamp.class, "java.sql.Timestamp", null),
    BLOB("Blob", Blob.class, "java.sql.Blob", null),
    CLOB("Clob", Clob.class, "java.sql.Clob", null),

    // java8 新时间类型
    LOCAL_DATE("LocalDate", LocalDate.class, "java.time.LocalDate", null),
    LOCAL_TIME("LocalTime", LocalTime.class, "java.time.LocalTime", null),
    YEAR("Year", Year.class, "java.time.Year", null),
    YEAR_MONTH("YearMonth", YearMonth.class, "java.time.YearMonth", null),
    LOCAL_DATE_TIME("LocalDateTime", LocalDateTime.class, "java.time.LocalDateTime", new LocalDateObjectConvert()),

    // 其他类型
    BYTE_ARRAY("byte[]", byte[].class, null, null),
    OBJECT("Object", Object.class, null, null),
    DATE("Date", java.util.Date.class, "java.util.Date", null),
    BIG_INTEGER("BigInteger", BigInteger.class, "java.math.BigInteger", null),
    BIG_DECIMAL("BigDecimal", BigDecimal.class, "java.math.BigDecimal", null);

    /**
     * 字段类型名称
     */
    private final String name;
    /**
     * 字段类型
     */
    private final Class<?> type;
    /**
     * 完整包名
     */
    private final String packages;
    /**
     * 转化器
     */
    private final ObjectConvert convert;


    FieldType(final String name, final Class<?> type, final String packages, final ObjectConvert convert) {
        this.name = name;
        this.type = type;
        this.packages = packages;
        this.convert = convert;
    }


    @Override
    public Class<?> type() {
        return type;
    }

    @Override
    public String packages() {
        return packages;
    }

    @Override
    public ObjectConvert convert() {
        return convert;
    }

    /**
     * 通过字段类型找到字段枚举
     *
     * @param type
     * @return
     */
    public static FieldType valueOf(Class<?> type) {
        FieldType[] values = FieldType.values();
        for (FieldType value : values) {
            if (value.type().equals(type)) {
                return value;
            }
        }
        //默认返回object类型
        return OBJECT;
    }
}

