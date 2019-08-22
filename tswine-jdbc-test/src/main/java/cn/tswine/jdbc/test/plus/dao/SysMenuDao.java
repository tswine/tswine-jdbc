package cn.tswine.jdbc.test.plus.dao;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.dao.AbstractDao;
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
        return DbType.POSTGRE_SQL;
    }
}
