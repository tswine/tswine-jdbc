package cn.tswine.jdbc.plus.converts;

import cn.tswine.jdbc.plus.builder.schema.EntitySchema;

import java.util.List;
import java.util.Map;

/**
 * 结果转换器接口
 *
 * @Author: silly
 * @Date: 2019/8/24 12:58
 * @Version 1.0
 * @Desc
 */
public interface IResultConvert {

    <T> T execute( EntitySchema schema,List<Map<String, Object>> results);
}
