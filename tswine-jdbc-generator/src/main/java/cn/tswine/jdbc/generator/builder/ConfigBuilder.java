package cn.tswine.jdbc.generator.builder;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.utils.StringUtils;
import cn.tswine.jdbc.generator.config.DataSourceConfig;
import cn.tswine.jdbc.generator.config.GlobalConfig;
import cn.tswine.jdbc.generator.config.IDbQuery;
import cn.tswine.jdbc.generator.config.pojo.Table;
import cn.tswine.jdbc.generator.config.pojo.TableField;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: silly
 * @Date: 2019/8/7 15:42
 * @Version 1.0
 * @Desc
 */
@Data
public class ConfigBuilder {

    /**
     * 数据库表信息
     */
    private List<Table> tableList;

    /**
     * 数据源配置
     */
    DataSourceConfig dataSourceConfig;


    /**
     * 全局配置
     */
    GlobalConfig globalConfig;
    /**
     * SQL连接
     */
    private Connection connection;
    /**
     * SQL语句执行器
     */
    private IDbQuery dbQuery;


    public ConfigBuilder(DataSourceConfig dataSourceConfig, GlobalConfig globalConfig) {
        this.dataSourceConfig = dataSourceConfig;
        this.globalConfig = globalConfig;
        handleDataSource();
        tableList = getTables();
    }

    /**
     * 处理数据源
     */
    private void handleDataSource() {
        connection = dataSourceConfig.getConn();
        dbQuery = dataSourceConfig.getDbQuery();
    }

    /**
     * 获取数据库的所有表
     *
     * @return
     */
    private List<Table> getTables() {
        List<Table> tableList = new ArrayList<>();
        String tablesSql = dbQuery.tableSql();
        if (DbType.POSTGRE_SQL == dbQuery.dbType()) {
            String schema = dataSourceConfig.getSchemaName();
            if (schema == null) {
                schema = "public";
                dataSourceConfig.setSchemaName(schema);
            }
            tablesSql = String.format(tablesSql, schema);
        }
        try (PreparedStatement ps = connection.prepareStatement(tablesSql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tableName = rs.getString(dbQuery.tableName());
                if (!StringUtils.isEmpty(tableName)) {
                    Table table = new Table();
                    table.setName(tableName);
                    //表注解
                    String tableComment = rs.getString(dbQuery.tableComment());
                    if (globalConfig.isSkipView() && "VIEW".equals(tableComment)) {
                        //跳过视图
                        continue;
                    }
                    table.setComment(tableComment);
                    tableList.add(table);
                } else {
                    throw new TswineJdbcException("数据库表结构为空");
                }
            }
        } catch (SQLException e) {
            throw new TswineJdbcException("getTables执行异常", e);
        }
        List<Table> goalTables = null;
        if (globalConfig.getIncludeTables() != null && globalConfig.getIncludeTables().length > 0) {
            //只要包含的表
            String[] includeTables = globalConfig.getIncludeTables();
            goalTables = new ArrayList<>();
            for (String str : includeTables) {
                if (StringUtils.isEmpty(str)) {
                    continue;
                }
                for (Table table : tableList) {
                    if (table.getName().equalsIgnoreCase(str)) {
                        goalTables.add(table);
                    }
                }
            }
        } else if (globalConfig.getExcludeTables() != null && globalConfig.getExcludeTables().length > 0) {
            String[] excludeTables = globalConfig.getExcludeTables();
            //排除指定的表
            Iterator<Table> iterator = tableList.iterator();
            while (iterator.hasNext()) {
                Table table = iterator.next();
                for (String str : excludeTables) {
                    if (StringUtils.isEmpty(str)) {
                        continue;
                    }
                    if (table.getName().equalsIgnoreCase(str)) {
                        iterator.remove();
                    }
                }
            }
        }
        if (goalTables == null) {
            goalTables = tableList;
        }
        //获取表字段
        goalTables.forEach(table -> getTableField(table));
        return goalTables;
    }

    /**
     * 获取表字段
     *
     * @param table
     * @return
     */
    private Table getTableField(Table table) {
//        ITypeConvert typeConvert = dataSourceConfig.getTypeConvert();
        //查询表字段
        List<TableField> tableFields = new ArrayList<>();
        String tableName = table.getName();
        String tableFiledSql = dbQuery.tableFiledSql();
        if (StringUtils.isEmpty(dataSourceConfig.getSchemaName())) {
            tableFiledSql = String.format(tableFiledSql, tableName);
        } else {
            tableFiledSql = String.format(tableFiledSql, dataSourceConfig.getSchemaName(), tableName);
        }
        try (PreparedStatement ps = connection.prepareStatement(tableFiledSql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TableField tableField = new TableField();
                tableField.setColumnName(rs.getString(dbQuery.fieldName()));
                tableField.setColumnComment(rs.getString(dbQuery.fieldComment()));
                tableField.setColumnType(rs.getString(dbQuery.fieldType()));
                tableField.setKey(dbQuery.isFieldKey(rs.getString(dbQuery.fieldKey())));
                tableField.setFieldType(dataSourceConfig.getTypeConvert().execute(globalConfig, tableField.getColumnType()));
                tableFields.add(tableField);
            }
        } catch (SQLException e) {
            throw new TswineJdbcException("getTableField执行异常", e);
        }
        table.setTableFields(tableFields);
        return table;
    }
}
