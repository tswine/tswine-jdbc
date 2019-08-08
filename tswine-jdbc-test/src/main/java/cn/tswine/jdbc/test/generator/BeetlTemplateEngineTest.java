package cn.tswine.jdbc.test.generator;

import cn.tswine.jdbc.generator.engine.BeetlTemplateEngine;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: silly
 * @Date: 2019/8/8 22:19
 * @Version 1.0
 * @Desc
 */
public class BeetlTemplateEngineTest {


    @Test
    public void engint() {
        BeetlTemplateEngine beetlTemplateEngine = new BeetlTemplateEngine();
        Map<String, Object> params = new HashMap();
        params.put("name", "tswine");
        beetlTemplateEngine.init(null).writer("/templates/test.btl",
                "F:\\temp\\test.java", params);
    }
}