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
    MYSQL(ConstValue.DB.MYSQL, "%s LIKE CONCAT('%%',#{%s},'%%')", "MySQL数据库"),
    /**
     * MARIADB
     */
    MARIADB(ConstValue.DB.MARIADB, "%s LIKE CONCAT('%%',#{%s},'%%')", "MariaDB数据库"),
    /**
     * POSTGRE
     */
    POSTGRE_SQL(ConstValue.DB.POSTGRE_SQL, "%s LIKE CONCAT('%%',#{%s},'%%')", "PostgreSQL数据库");


    /**
     * 数据库名称
     */
    private String db;
    /**
     * LIKE 拼接模式
     */
    private String like;
    /**
     * 描述
     */
    private String desc;


    DbType(String db, String like, String desc) {
        this.db = db;
        this.like = like;
        this.desc = desc;
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

    public String getLike() {
        return like;
    }

    public String getDesc() {
        return desc;
    }
}
