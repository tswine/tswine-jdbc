package cn.tswine.jdbc.plus.converts;

import cn.tswine.jdbc.plus.builder.schema.EntitySchema;
import cn.tswine.jdbc.plus.sql.SqlSource;

/**
 * 结果转换器接口
 *
 * @Author: silly
 * @Date: 2019/8/24 12:58
 * @Version 1.0
 * @Desc
 */
public interface IResultConvert {

    void convertTo(EntitySchema schema, SqlSource sqlSource);
}
