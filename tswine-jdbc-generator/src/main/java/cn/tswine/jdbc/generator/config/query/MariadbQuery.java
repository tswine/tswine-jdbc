package cn.tswine.jdbc.generator.config.query;

import cn.tswine.jdbc.common.annotation.DbType;

/**
 * @Author: silly
 * @Date: 2019/8/7 11:59
 * @Version 1.0
 * @Desc
 */
public class MariadbQuery extends AbstractDbQuery {
    @Override
    public DbType dbType() {
        return DbType.MYSQL;
    }

    @Override
    public String tableSql() {
        return "SHOW TABLE STATUS";
    }

    @Override
    public String tableFiledSql() {
        return "SHOW FULL FIELDS FROM `%s`";
    }

    @Override
    public String tableName() {
        return "NAME";
    }

    @Override
    public String tableComment() {
        return "COMMENT";
    }

    @Override
    public String fieldName() {
        return "FIELD";
    }

    @Override
    public String fieldType() {
        return "TYPE";
    }

    @Override
    public String fieldComment() {
        return "COMMENT";
    }

    @Override
    public String fieldKey() {
        return "KEY";
    }

    @Override
    public boolean isFieldKey(String keyValue) {
        return false;
    }
}
