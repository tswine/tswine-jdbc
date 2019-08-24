package cn.tswine.jdbc.plus.converts;

import cn.tswine.jdbc.plus.builder.schema.EntitySchema;

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
    public <T> T execute(EntitySchema schema, List<Map<String, Object>> results) {
        if (results != null && results.size() > 0) {
            Map<String, Object> result = results.get(0);
            return (T) schema.asEntity(result);
        }
        return null;
    }
}
