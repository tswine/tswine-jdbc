package cn.tswine.jdbc.generator.config;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.generator.config.converts.MySqlTypeConvert;
import cn.tswine.jdbc.generator.config.converts.PostgreSqlTypeConvert;
import cn.tswine.jdbc.generator.config.query.MariadbQuery;
import cn.tswine.jdbc.generator.config.query.MySqlQuery;
import cn.tswine.jdbc.generator.config.query.PostgreSqlQuery;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 数据源配置
 *
 * @Author: silly
 * @Date: 2019/8/7 10:59
 * @Version 1.0
 * @Desc
 */
@Data
@Accessors(chain = true)
public class DataSourceConfig {
    /**
     * 数据库信息查询
     */
    private IDbQuery dbQuery;

    /**
     * 针对PostgreSQL schemaName
     */
    private String schemaName;
    /**
     * 数据库类型
     */
    private DbType dbType;

    /**
     * 字段类型转换器
     */
    private ITypeConvert typeConvert;

    /**
     * 驱动连接的URL
     */
    private String url;
    /**
     * 驱动名称
     */
    private String driverName;
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    /**
     * 获取数据库类型
     *
     * @return
     */
    public DbType getDbType() {
        if (null == dbType) {
            //从驱动名称中查找
            dbType = getDbType(driverName.toLowerCase());
            if (null == dbType) {
                //从连接url中获取
                dbType = getDbType(driverName.toLowerCase());
            }
        }
        if (null == dbType) {
            throw new TswineJdbcException("Unknown database type");

        }
        return dbType;
    }


    /**
     * 获取表结构查询执行器
     *
     * @return
     */
    public IDbQuery getDbQuery() {
        //可传入自定义实现的DbQuery
        if (null == dbQuery) {
            switch (getDbType()) {
                case MYSQL:
                    dbQuery = new MySqlQuery();
                    break;
                case MARIADB:
                    dbQuery = new MariadbQuery();
                    break;
                case POSTGRE_SQL:
                    dbQuery = new PostgreSqlQuery();
                    break;
                //TODO 其他数据库
                default:
                    //默认mysql数据库
                    dbQuery = new MySqlQuery();
            }
        }
        return dbQuery;
    }

    /**
     * 获取类型转换器
     *
     * @return
     */
    public ITypeConvert getTypeConvert() {
        if (null == typeConvert) {
            switch (getDbType()) {
                case MYSQL:
                    typeConvert = new MySqlTypeConvert();
                    break;
                case POSTGRE_SQL:
                    typeConvert = new PostgreSqlTypeConvert();
                    break;
                //TODO　其他数据库
                default:
                    typeConvert = new MySqlTypeConvert();
            }
        }
        return typeConvert;
    }

    /**
     * 创建数据库连接
     *
     * @return
     */
    public Connection getConn() {
        Connection conn = null;
        try {
            Class.forName(driverName);
            //由于只是做简单的代码生成，对性能要求不高，采用sql自带的工具创建Connection
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    /**
     * 从字符串中判断数据库类型
     *
     * @param str 字符串
     * @return 数据库类型枚举值，如果没找到，则返回 null
     */
    private DbType getDbType(String str) {
        if (str.contains("mysql")) {
            return DbType.MYSQL;
        } else if (str.contains("oracle")) {
            return DbType.ORACLE;
        } else if (str.contains("postgresql")) {
            return DbType.POSTGRE_SQL;
        } else if (str.contains("sqlserver")) {
            return DbType.SQL_SERVER;
        } else if (str.contains("db2")) {
            return DbType.DB2;
        } else if (str.contains("mariadb")) {
            return DbType.MARIADB;
        } else if (str.contains("sqlite")) {
            return DbType.MARIADB;
        } else if (str.contains("h2")) {
            return DbType.H2;
        } else {
            return null;
        }
    }
}
