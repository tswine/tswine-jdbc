package cn.tswine.jdbc.generator.builder;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.exception.TswineJdbcException;
import cn.tswine.jdbc.common.utils.StringUtils;
import cn.tswine.jdbc.generator.config.*;
import cn.tswine.jdbc.generator.config.pojo.TableField;
import cn.tswine.jdbc.generator.config.pojo.TableInfo;
import cn.tswine.jdbc.generator.config.rules.NameStrategy;
import lombok.Data;
import org.apache.log4j.Logger;

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
    protected static final Logger logger = Logger.getLogger(ConfigBuilder.class);

    /**
     * 数据库表信息
     */
    private List<TableInfo> tableList;

    /**
     * 数据源配置
     */
    private DataSourceConfig dataSourceConfig;


    /**
     * 全局配置
     */
    private GlobalConfig globalConfig;

    /**
     * 生成代码策略配置
     */
    private StrategyConfig strategyConfig;
    /**
     * 模板文件配置
     */
    private TemplateConfig templateConfig;
    /**
     * SQL连接
     */
    private Connection connection;
    /**
     * SQL语句执行器
     */
    private IDbQuery dbQuery;


    public ConfigBuilder(DataSourceConfig dataSourceConfig, GlobalConfig globalConfig, StrategyConfig strategyConfig, TemplateConfig templateConfig) {
        this.dataSourceConfig = dataSourceConfig;
        this.globalConfig = globalConfig;
        this.strategyConfig = strategyConfig;
        this.templateConfig = templateConfig;
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
    private List<TableInfo> getTables() {
        List<TableInfo> tableList = new ArrayList<>();
        String tablesSql = dbQuery.tableSql();
        if (DbType.POSTGRE_SQL == dbQuery.dbType()) {
            String schema = dataSourceConfig.getSchemaName();
            if (schema == null) {
                schema = "public";
                dataSourceConfig.setSchemaName(schema);
            }
            tablesSql = String.format(tablesSql, schema);
        }
        logger.info("表查询语句:" + tablesSql);
        try (PreparedStatement ps = connection.prepareStatement(tablesSql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tableName = rs.getString(dbQuery.tableName());
                if (!StringUtils.isEmpty(tableName)) {
                    TableInfo table = new TableInfo();
                    table.setTableName(tableName);
                    //表注解
                    String tableComment = rs.getString(dbQuery.tableComment());
                    if (globalConfig.isSkipView() && "VIEW".equals(tableComment)) {
                        //跳过视图
                        continue;
                    }
                    table.setName(handleStrategyEntityName(tableName));
                    table.setComment(tableComment);
                    tableList.add(table);
                } else {
                    throw new TswineJdbcException("数据库表结构为空");
                }
            }
        } catch (SQLException e) {
            throw new TswineJdbcException("getTables执行异常", e);
        }
        List<TableInfo> goalTables = null;
        if (globalConfig.getIncludeTables() != null && globalConfig.getIncludeTables().length > 0) {
            //只要包含的表
            String[] includeTables = globalConfig.getIncludeTables();
            goalTables = new ArrayList<>();
            for (String str : includeTables) {
                if (StringUtils.isEmpty(str)) {
                    continue;
                }
                for (TableInfo table : tableList) {
                    if (table.getTableName().equalsIgnoreCase(str)) {
                        goalTables.add(table);
                    }
                }
            }
        } else if (globalConfig.getExcludeTables() != null && globalConfig.getExcludeTables().length > 0) {
            String[] excludeTables = globalConfig.getExcludeTables();
            //排除指定的表
            Iterator<TableInfo> iterator = tableList.iterator();
            while (iterator.hasNext()) {
                TableInfo table = iterator.next();
                for (String str : excludeTables) {
                    if (StringUtils.isEmpty(str)) {
                        continue;
                    }
                    if (table.getTableName().equalsIgnoreCase(str)) {
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
     * 实体名生成策略
     *
     * @param tableName
     * @return
     */
    private String handleStrategyEntityName(String tableName) {
        //去除前缀
        String[] removePrefix = strategyConfig.configEntity().getRemovePrefix();
        if (removePrefix != null) {
            for (String prefix : removePrefix) {
                if (tableName.startsWith(prefix)) {
                    tableName = tableName.replaceFirst(prefix, "");
                }
            }
        }
        if (tableName.startsWith("_")) {
            tableName = tableName.substring(1);
        }
        tableName = NameStrategy.changeNameStrategy(tableName, strategyConfig.configEntity().getClassNameStrategy());
        //添加前缀
        if (!StringUtils.isEmpty(strategyConfig.configEntity().getAddPrefix())) {
            tableName = String.format("%s%s", strategyConfig.configEntity().getAddPrefix(), tableName);
        }
        //添加后缀
        if (!StringUtils.isEmpty(strategyConfig.configEntity().getAddSuffix())) {
            tableName = String.format("%s%s", tableName, strategyConfig.configEntity().getAddSuffix());
        }
        return tableName;
    }

    /**
     * 获取表字段
     *
     * @param table
     * @return
     */
    private TableInfo getTableField(TableInfo table) {
        //查询表字段
        List<TableField> tableFields = new ArrayList<>();
        String tableName = table.getTableName();
        String tableFiledSql = dbQuery.tableFiledSql();
        if (StringUtils.isEmpty(dataSourceConfig.getSchemaName())) {
            tableFiledSql = String.format(tableFiledSql, tableName);
        } else {
            tableFiledSql = String.format(tableFiledSql, dataSourceConfig.getSchemaName(), tableName);
        }
        logger.info("字段查询语句:" + tableFiledSql);
        try (PreparedStatement ps = connection.prepareStatement(tableFiledSql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String fieldName = rs.getString(dbQuery.fieldName());
                boolean isExclude = false;
                //排除字段
                if (globalConfig.getExcludeFields() != null) {
                    for (String str : globalConfig.getExcludeFields()) {
                        if (!StringUtils.isEmpty(str) && str.equalsIgnoreCase(fieldName)) {
                            isExclude = true;
                            break;
                        }
                    }
                }
                if (isExclude) {
                    continue;
                }
                TableField tableField = new TableField();
                tableField.setColumnName(fieldName);
                tableField.setColumnComment(rs.getString(dbQuery.fieldComment()));
                tableField.setColumnType(rs.getString(dbQuery.fieldType()));
                tableField.setFieldType(dataSourceConfig.getTypeConvert().execute(globalConfig, tableField.getColumnType()));
                tableField.setFieldName(NameStrategy.changeNameStrategy(tableField.getColumnName(), strategyConfig.configEntity().getFieldNameStrategy()));
                boolean isFieldKey = dbQuery.isFieldKey(rs.getString(dbQuery.fieldKey()));
                if (isFieldKey) {
                    tableField.setKey(true);
                    table.addPrimaryKey(tableField);
                }
                tableFields.add(tableField);
            }
        } catch (SQLException e) {
            throw new TswineJdbcException("getTableField执行异常", e);
        }
        table.setTableFields(tableFields);
        return table;
    }
}
