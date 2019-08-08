package cn.tswine.jdbc.common.annotation;

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
    MYSQL("mysql", "%s LIKE CONCAT('%%',#{%s},'%%')", "MySql数据库"),
    /**
     * MARIADB
     */
    MARIADB("mariadb", "%s LIKE CONCAT('%%',#{%s},'%%')", "MariaDB数据库"),
    /**
     * POSTGRE
     */
    POSTGRE_SQL("postgresql", "%s LIKE CONCAT('%%',#{%s},'%%')", "Postgre数据库");


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
}
