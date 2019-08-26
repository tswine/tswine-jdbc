package cn.tswine.jdbc.plus.converts;

import cn.tswine.jdbc.plus.builder.schema.EntitySchema;
import cn.tswine.jdbc.plus.sql.SqlSource;

import java.util.List;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/24 13:03
 * @Version 1.0
 * @Desc
 */
public class ResultConvertEntity implements IResultConvert {


    @Override
    public void convertTo(EntitySchema schema, SqlSource sqlSource) {
        List<Map<String, Object>> results = sqlSource.getResultSelect();
        if (results == null || results.size() <= 0) {
            return;
        }
        Map<String, Object> result = results.get(0);
        Object obj = schema.asEntity(result);
        sqlSource.setResult(obj);
    }
}