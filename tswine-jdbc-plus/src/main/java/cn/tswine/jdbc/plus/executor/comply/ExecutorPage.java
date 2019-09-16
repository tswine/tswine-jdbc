package cn.tswine.jdbc.plus.executor.comply;

import cn.tswine.jdbc.common.toolkit.Assert;
import cn.tswine.jdbc.common.toolkit.ExceptionUtils;
import cn.tswine.jdbc.common.toolkit.StringUtils;
import cn.tswine.jdbc.plus.executor.BaseExecutor;
import cn.tswine.jdbc.plus.metadata.IPage;
import cn.tswine.jdbc.plus.metadata.SqlSource;
import cn.tswine.jdbc.plus.parser.CountSqlParser;
import cn.tswine.jdbc.plus.transaction.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 分页查询执行器
 *
 * @Author: silly
 * @Date: 2019/9/16 20:21
 * @Version 1.0
 * @Desc
 */
public class ExecutorPage extends BaseExecutor {
    private final Logger LOG = LoggerFactory.getLogger(BaseExecutor.class);

    public ExecutorPage(Transaction transaction) {
        super(transaction);
    }

    @Override
    public void execute(SqlSource sqlSource) {
        IPage page = sqlSource.getPage();
        Assert.isNotNull(page, "IPage参数不能为空");
        String sql = sqlSource.getSql();
        Object[] params = sqlSource.get0();
        if (LOG.isDebugEnabled()) {
            LOG.debug("executeQueryPage sql:{},params:{}", sql, params);
        }
        //计算开始偏移量
        int offset = (int) page.offset();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> results = new ArrayList<>();
        try {
            Connection conn = transaction.getConnection();
            ps = conn.prepareStatement(sql);
            //设置查询的最大行数
            ps.setMaxRows((int) (offset + page.getSize()));
            setObject(ps, params);
            rs = ps.executeQuery();
            //直接将游标标位当前页起始记录处
            rs.absolute(offset);
            while (rs.next()) {
                results.add(rsToMap(rs));
            }
            //查询总记录数
            if (page.isSearchCount()) {
                String countSql = new CountSqlParser().getSmartCountSql(sql);
                if (LOG.isDebugEnabled()) {
                    LOG.debug("executeQueryCount sql:{},params:{}", countSql, params);
                }
                ps = conn.prepareStatement(countSql);
                setObject(ps, params);
                rs = ps.executeQuery();
                if (rs.next()) {
                    page.setTotal(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw ExceptionUtils.tse(e, "executeQuery,sql:%s,params:%s", sql, StringUtils.join(params));
        } finally {
            close(rs, ps);
            transaction.close();
        }
        sqlSource.setResults(results);
    }
}
