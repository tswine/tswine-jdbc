package cn.tswine.jdbc.common.toolkit.sql;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.enums.SQLSentenceType;
import cn.tswine.jdbc.common.enums.SqlMethod;
import cn.tswine.jdbc.common.toolkit.Assert;
import cn.tswine.jdbc.common.toolkit.StringPool;
import cn.tswine.jdbc.common.toolkit.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
     * @param dbType     数据库类型
     * @param tableName  表名
     * @param columnList 列名集合
     * @return
     */
    public static String getInsertSql(DbType dbType, String tableName, Collection<String> columnList) {
        //INSERT INTO %s ( %s ) VALUES ( %s )
        SqlMethod sqlMethod = SqlMethod.INSERT;
        String columns = getColumns(columnList, dbType.getDbType().getPlaceholder());
        String questionMark = getQuestionMark(columnList.size());
        return String.format(sqlMethod.getSql(), tableName, columns, questionMark);
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
     * 获取where条件 SQL
     *
     * @param columns 条件列
     * @return user_name = ? AND password = ?
     */
    public static String getWhere(Collection<String> columns) {
        return getColumnJoin(columns, SQLSentenceType.AND.getValue());
    }

    /**
     * 获取set SQL
     *
     * @param columns 列
     * @return user_name = ? , password = ?
     */
    public static String getSet(Collection<String> columns) {
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
     * 字段转换为列集合
     *
     * @param fields 表字段
     * @return `user_name`,`password`,`status`
     */
    public static String getColumns(Collection<String> fields, String placeholder) {
        ArrayList<String> columns = new ArrayList<>();
        fields.forEach(k -> {
            if (StringUtils.isNotEmpty(placeholder)) {
                k = String.format(placeholder, k);
            }
            columns.add(k);
        });
        return StringUtils.join(columns.toArray(), ",");
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
    private static String getColumnJoin(Collection<String> columns, String joiner) {
        Assert.isNotNull(columns, "条件列不能为空");
        int size = columns.size() - 1;
        if (size == -1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        Iterator<String> iterator = columns.iterator();
        while (iterator.hasNext()) {
            String column = iterator.next();
            sb.append(column);
            sb.append(SPACE);
            sb.append(EQ);
            sb.append(SPACE);
            sb.append(QUESTION_MARK);
            sb.append(SPACE);
            if (i == size) {
                return sb.toString();
            }
            sb.append(SPACE);
            sb.append(joiner);
            sb.append(SPACE);
            i++;
        }
        return "";
    }


}
