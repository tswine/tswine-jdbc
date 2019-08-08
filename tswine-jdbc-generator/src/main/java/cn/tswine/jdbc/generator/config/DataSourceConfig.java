package cn.tswine.jdbc.generator.config;

import cn.tswine.jdbc.common.ConstValue;
import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.utils.StringUtils;
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
            throw new TswineJdbcException("未知数据库类型");

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
            }
        }
        if (null == dbQuery) {
            throw new TswineJdbcException("数据库表结构查询执行器未找到:AbstractDbQuery");
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
            }
        }
        if (null == typeConvert) {
            throw new TswineJdbcException("数据库字段类型转换器实现类未找到:ITypeConvert");
        }
        return typeConvert;
    }

    /**
     * 创建数据库连接
     *
     * @return
     */
    public Connection getConn() {
        Connection conn;
        try {
            Class.forName(driverName);
            //由于只是做简单的代码生成，对性能要求不高，采用sql自带的连接池创建Connection
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new TswineJdbcException("获取数据库连接异常", ex);
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
        if (StringUtils.contains(str, ConstValue.DB.MYSQL, true)) {
            return DbType.MYSQL;
        }
        if (StringUtils.contains(str, ConstValue.DB.MARIADB, true)) {
            return DbType.MARIADB;
        }
        if (StringUtils.contains(str, ConstValue.DB.POSTGRE_SQL, true)) {
            return DbType.POSTGRE_SQL;
        }
        return null;
    }
}
