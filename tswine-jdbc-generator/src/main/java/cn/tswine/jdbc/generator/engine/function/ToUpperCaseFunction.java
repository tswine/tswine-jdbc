package cn.tswine.jdbc.generator.engine.function;

import org.beetl.core.Context;
import org.beetl.core.Function;

/**
 * 字符大写
 *
 * @Author: silly
 * @Date: 2019/8/9 19:55
 * @Version 1.0
 * @Desc
 */
public class ToUpperCaseFunction implements Function {
    @Override
    public Object call(Object[] objects, Context context) {
        String str = (String) objects[0];
        return str.toUpperCase();
    }
}
