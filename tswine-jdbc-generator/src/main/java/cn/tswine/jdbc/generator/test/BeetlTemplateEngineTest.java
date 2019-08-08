package cn.tswine.jdbc.generator.test;

import cn.tswine.jdbc.generator.engine.BeetlTemplateEngine;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/8 22:19
 * @Version 1.0
 * @Desc
 */
public class BeetlTemplateEngineTest {


    public static void main(String[] args) {
        BeetlTemplateEngine beetlTemplateEngine = new BeetlTemplateEngine();
        Map<String, Object> params = new HashMap();
        params.put("name", "tswine");
        beetlTemplateEngine.init(null).writer("/templates/test.btl",
                "F:\\temp\\test.java", params);
    }
}