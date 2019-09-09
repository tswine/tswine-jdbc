package cn.tswine.jdbc.test.plus.dao;

import cn.tswine.jdbc.common.annotation.DbType;
import cn.tswine.jdbc.common.rules.IDBLabel;
import cn.tswine.jdbc.plus.dao.AbstractDao;
import cn.tswine.jdbc.test.plus.entity.User;

/**
 * @Author: silly
 * @Date: 2019/9/2 16:28
 * @Version 1.0
 * @Desc
 */
public class UserDao extends AbstractDao<User> {
    public IDBLabel getDbLabel() {
        return DbType.MYSQL;
    }
}
