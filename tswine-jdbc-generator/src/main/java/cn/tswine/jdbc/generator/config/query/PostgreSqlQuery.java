package cn.tswine.jdbc.generator.config.query;

import cn.tswine.jdbc.common.annotation.DbType;

/**
 * @Author: silly
 * @Date: 2019/8/7 11:58
 * @Version 1.0
 * @Desc
 */
public class PostgreSqlQuery extends AbstractDbQuery {
    @Override
    public DbType dbType() {
        return DbType.POSTGRE_SQL;
    }

    @Override
    public String tableSql() {
        return "SELECT A.tablename, obj_description(relfilenode, 'pg_class') AS comments FROM pg_tables A, pg_class B WHERE A.schemaname='%s' AND A.tablename = B.relname";
    }

    @Override
    public String tableFiledSql() {
        return "SELECT A.attname AS name, format_type(A.atttypid, A.atttypmod) AS type,col_description(A.attrelid, A.attnum) AS comment, (CASE C.contype WHEN 'p' THEN 'PRI' ELSE '' END) AS key " +
                "FROM pg_attribute A LEFT JOIN pg_constraint C ON A.attnum = C.conkey[1] AND A.attrelid = C.conrelid " +
                "WHERE  A.attrelid = '%s.%s'::regclass AND A.attnum > 0 AND NOT A.attisdropped ORDER  BY A.attnum";
    }

    @Override
    public String tableName() {
        return "tablename";
    }


    @Override
    public String tableComment() {
        return "comments";
    }


    @Override
    public String fieldName() {
        return "name";
    }


    @Override
    public String fieldType() {
        return "type";
    }


    @Override
    public String fieldComment() {
        return "comment";
    }


    @Override
    public String fieldKey() {
        return "key";
    }

    @Override
    public boolean isFieldKey(String keyValue) {
        return keyValue.equalsIgnoreCase("PRI");
    }
}
