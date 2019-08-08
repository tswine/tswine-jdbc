package cn.tswine.jdbc.generator.config;

import cn.tswine.jdbc.common.annotation.DbType;

/**
 * 表数据查询接口
 *
 * @Author: silly
 * @Date: 2019/8/7 11:03
 * @Version 1.0
 * @Desc
 */
public interface IDbQuery {

    /**
     * 数据库类型
     *
     * @return
     */
    DbType dbType();


    /**
     * 查询所有表sql
     *
     * @return
     */
    String tableSql();

    /**
     * 查询表结构sql
     *
     * @return
     */
    String tableFiledSql();

    /**
     * 表名称
     */
    String tableName();

    /**
     * 表注释
     */
    String tableComment();

    /**
     * 字段名称
     */
    String fieldName();

    /**
     * 字段类型
     */
    String fieldType();

    /**
     * 字段注释
     */
    String fieldComment();

    /**
     * 主键字段
     */
    String fieldKey();

    /**
     * 自定义字段名称
     */
    String[] fieldCustom();

}
