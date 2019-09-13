package cn.tswine.jdbc.common.toolkit.sql;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.enums.SQLSentenceType;
import cn.tswine.jdbc.common.enums.SqlMethod;
import cn.tswine.jdbc.common.toolkit.ArrayUtils;
import cn.tswine.jdbc.common.toolkit.Assert;
import cn.tswine.jdbc.common.toolkit.StringPool;
import cn.tswine.jdbc.common.toolkit.StringUtils;

/**
 * sql工具类
 *
 * @Author: silly
 * @Date: 2019/8/24 11:09
 * @Version 1.0
 * @Desc
 */
public class SqlUtils implements StringPool {


    /**
     * 获取Insert SQL
     *
     * @param dbType    数据库类型
     * @param tableName 表名
     * @param columns   列
     * @return 执行的插入sql语句
     */
    public static String getInsertSql(DbType dbType, String tableName, String[] columns) {
        Assert.notEmpty(tableName, "tableName 不能为空");
        //转义表名
        tableName = columnEscape(tableName, dbType.getPlaceholder());
        //INSERT INTO %s ( %s ) VALUES ( %s )
        SqlMethod sqlMethod = SqlMethod.INSERT;
        String columnSql = getColumnSql(columns, dbType.getDbType().getPlaceholder());
        String questionMark = getQuestionMark(columns.length);
        return String.format(sqlMethod.getSql(), tableName, columnSql, questionMark);
    }


    /**
     * 获取 Delete SQL
     *
     * @param tableName 表名
     * @param whereSql  where条件sql
     * @return
     */
    public static String getDeleteSql(String tableName, String whereSql) {
        //DELETE FROM %s WHERE %s
        SqlMethod sqlMethod = SqlMethod.DELETE;
        return String.format(sqlMethod.getSql(), tableName, whereSql);
    }

    /**
     * 获取 Update SQL
     *
     * @param tableName 表名
     * @param setSql    set SQL
     * @param whereSql  where条件sql
     * @return
     */
    public static String getUpdateSql(String tableName, String setSql, String whereSql) {
        //UPDATE %s SET %s WHERE %s
        SqlMethod sqlMethod = SqlMethod.UPDATE;
        return String.format(sqlMethod.getSql(), tableName, setSql, whereSql);
    }

    /**
     * 获取 Select SQL
     *
     * @param tableName 表名
     * @param columnSql 列 SQL
     * @param whereSql  where SQL
     * @return
     */
    public static String getSelectSql(String tableName, String columnSql, String whereSql) {
        //SELECT %s FROM %s %s
        SqlMethod sqlMethod = SqlMethod.SELECT;
        return String.format(sqlMethod.getSql(), columnSql, tableName, whereSql);
    }

    /**
     * 获取where条件 SQL
     *
     * @param columns 条件列
     * @return where user_name = ? AND password = ?
     */
    public static String getWhere(String[] columns) {
        return String.format("%s %s", SQLSentenceType.WHERE.getValue(), getColumnJoin(columns, SQLSentenceType.AND.getValue()));
    }

    /**
     * 获取set SQL
     *
     * @param columns 列
     * @return user_name = ? , password = ?
     */
    public static String getSet(String[] columns) {
        return getColumnJoin(columns, COMMA);
    }

    /**
     * 获取in条件sql
     *
     * @param column 类名
     * @param count  个数
     * @return user_name in (?,?,?)
     */
    public static String getIn(String column, int count) {
        Assert.isNotNull(column, "条件列不能为空");
        int size = count - 1;
        if (size == -1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(column);
        sb.append(SPACE);
        sb.append(SQLSentenceType.IN);
        sb.append(SPACE);
        sb.append(LEFT_BRACKET);

        for (int i = 0; ; i++) {
            sb.append(QUESTION_MARK);
            if (i == size) {
                sb.append(RIGHT_BRACKET);
                return sb.toString();
            }
            sb.append(COMMA);
        }
    }

    /**
     * 获取列字段字符串
     *
     * @param columns     列字段
     * @param placeholder 转义符
     * @return `user_name`,`password`,`status`
     */
    public static String getColumnSql(String[] columns, String placeholder) {
        //列集合空：返回*
        if (ArrayUtils.isEmpty(columns)) {
            return StringPool.ASTERISK;
        }
        String[] columnNew = new String[columns.length];
        if (StringUtils.isNotEmpty(placeholder)) {
            for (int i = 0; i < columns.length; i++) {
                columnNew[i] = columnEscape(columns[i], placeholder);
            }
        }
        return StringUtils.join(columnNew, ",");

    }

    /**
     * 获取？
     *
     * @param count ？个数
     * @return ?,?,?,?,?
     */
    public static String getQuestionMark(int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; ; i++) {
            sb.append(QUESTION_MARK);
            if (i == (count - 1)) {
                return sb.toString();
            }
            sb.append(COMMA);
        }
    }

    /**
     * 获取列获取连接 SQL
     *
     * @param columns 条件列
     * @return user_name = ? connector password = ?
     */
    private static String getColumnJoin(String[] columns, String joiner) {
        if (ArrayUtils.isEmpty(columns)) {
            return EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < columns.length; i++) {
            String column = columns[i];
            sb.append(column);
            sb.append(SPACE);
            sb.append(EQ);
            sb.append(SPACE);
            sb.append(QUESTION_MARK);
            sb.append(SPACE);
            if ((i + 1) >= columns.length) {
                return sb.toString();
            }
            sb.append(SPACE);
            sb.append(joiner);
            sb.append(SPACE);
        }
        return EMPTY;
    }

    /**
     * 数据库字段转义
     *
     * @param column      需要转义的字段
     * @param placeholder 转义符
     * @return 转义后的字段
     */
    public static String columnEscape(String column, String placeholder) {
        if (StringUtils.isEmpty(column)) {
            return EMPTY;
        }
        if (StringUtils.isEmpty(placeholder)) {
            return column;
        }
        //TODO 判断该column 是否已经被转义
        return String.format(placeholder, column);
    }


}
