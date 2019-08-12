package cn.tswine.jdbc.generator.engine.function;

import cn.tswine.jdbc.common.utils.StringUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

/**
 * beetl模板自定义方法:首字符小写
 *
 * @Author: silly
 * @Date: 2019/8/9 19:14
 * @Version 1.0
 * @Desc
 */
public class FirstCharToLowerFunction implements Function {
    @Override
    public Object call(Object[] objects, Context context) {
        String str = (String) objects[0];
        return StringUtils.changeFirstCharacterCase(str, false);
    }
}
