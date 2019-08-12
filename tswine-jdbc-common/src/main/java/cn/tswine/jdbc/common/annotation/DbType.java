package cn.tswine.jdbc.common.annotation;

import cn.tswine.jdbc.common.ConstValue;

/**
 * 数据库类型枚举
 *
 * @Author: silly
 * @Date: 2019/8/7 11:08
 * @Version 1.0
 * @Desc
 */
public enum DbType {
    /**
     * MYSQL
     */
    MYSQL(ConstValue.DB.MYSQL, "MySQL数据库", "`%s`"),
    /**
     * MARIADB
     */
    MARIADB(ConstValue.DB.MARIADB, "MariaDB数据库", "`%s`"),
    /**
     * POSTGRE
     */
    POSTGRE_SQL(ConstValue.DB.POSTGRE_SQL, "PostgreSQL数据库", "%s");


    /**
     * 数据库名称
     */
    private String db;

    /**
     * 字段占位符
     */
    private String placeholder;

    /**
     * 描述
     */
    private String desc;


    DbType(String db, String desc, String placeholder) {
        this.db = db;
        this.desc = desc;
        this.placeholder = placeholder;
    }

    /**
     * 获取数据库类型
     *
     * @param dbType 数据库类型字符串
     */
    public static DbType getDbType(String dbType) {
        DbType[] values = DbType.values();
        for (DbType dt : values) {
            if (dt.getDb().equalsIgnoreCase(dbType)) {
                return dt;
            }
        }
        return null;
    }

    public String getDb() {
        return db;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public String getDesc() {
        return desc;
    }
}
