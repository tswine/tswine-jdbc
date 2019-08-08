package cn.tswine.jdbc.generator.config.rules;

/**
 * 表字段类型
 *
 * @Author: silly
 * @Date: 2019/8/7 12:40
 * @Version 1.0
 * @Desc
 */
public enum DbColumnType implements IColumnType {

    // 基本类型
    BASE_BYTE("byte", null),
    BASE_SHORT("short", null),
    BASE_CHAR("char", null),
    BASE_INT("int", null),
    BASE_LONG("long", null),
    BASE_FLOAT("float", null),
    BASE_DOUBLE("double", null),
    BASE_BOOLEAN("boolean", null),

    // 包装类型
    BYTE("Byte", null),
    SHORT("Short", null),
    CHARACTER("Character", null),
    INTEGER("Integer", null),
    LONG("Long", null),
    FLOAT("Float", null),
    DOUBLE("Double", null),
    BOOLEAN("Boolean", null),
    STRING("String", null),

    // sql 包下数据类型
    DATE_SQL("Date", "java.sql.Date"),
    TIME("Time", "java.sql.Time"),
    TIMESTAMP("Timestamp", "java.sql.Timestamp"),
    BLOB("Blob", "java.sql.Blob"),
    CLOB("Clob", "java.sql.Clob"),

    // java8 新时间类型
    LOCAL_DATE("LocalDate", "java.time.LocalDate"),
    LOCAL_TIME("LocalTime", "java.time.LocalTime"),
    YEAR("Year", "java.time.Year"),
    YEAR_MONTH("YearMonth", "java.time.YearMonth"),
    LOCAL_DATE_TIME("LocalDateTime", "java.time.LocalDateTime"),

    // 其他类型
    BYTE_ARRAY("byte[]", null),
    OBJECT("Object", null),
    DATE("Date", "java.util.Date"),
    BIG_INTEGER("BigInteger", "java.math.BigInteger"),
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal");

    /**
     * 字段类型
     */
    private final String type;
    /**
     * 完整包名
     */
    private final String packageName;


    DbColumnType(final String type, final String packageName) {
        this.type = type;
        this.packageName = packageName;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getPackageName() {
        return null;
    }
}
