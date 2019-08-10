package cn.tswine.jdbc.common;

/**
 * 定义常量值
 *
 * @Author: silly
 * @Date: 2019/8/8 17:01
 * @Version 1.0
 * @Desc
 */
public class ConstValue {
    /**
     * 数据库
     */
    public class DB {
        /**
         * SQLServer
         */
        public final static String SQLSERVER = " SQLServer";
        /**
         * MySQL
         */
        public final static String MYSQL = " MySQL";
        /**
         * MariaDB
         */
        public final static String MARIADB = "MariaDB";
        /**
         * PostgreSQL
         */
        public final static String POSTGRE_SQL = "PostgreSQL";
    }

    /**
     * 自动生成:占位符
     */
    public class GenerqatorPlaceholder {
        /**
         * 表名
         */
        public final static String TABLE_NAME = "<TABLE_NAME>";
        /**
         * 表字段
         */
        public final static String TABLE_FIELD = "<TABLE_FIELD>";
        /**
         * 表字段主键
         */
        public final static String TABLE_FIELD_KEY = "<TABLE_FIELD_KEY>";
    }
}
