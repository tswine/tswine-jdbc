package cn.tswine.jdbc.test.plus.dao;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.plus.core.dao.AbstractDao;
import cn.tswine.jdbc.plus.core.rules.IDBLabel;
import cn.tswine.jdbc.test.plus.entity.SysMenu;

/**
 * @Author: silly
 * @Date: 2019/8/14 20:07
 * @Version 1.0
 * @Desc
 */
public class SysMenuDao extends AbstractDao<SysMenu> {
    @Override
    public IDBLabel getDbLabel() {
        return new IDBLabel() {
            @Override
            public String getLabel() {
                return "Test";
            }

            @Override
            public DbType getDbType() {
                return null;
            }
        };
    }
}
