package cn.tswine.jdbc.common.rules.convert.object;

import cn.tswine.jdbc.common.rules.ObjectConvert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: silly
 * @Date: 2019/8/26 15:15
 * @Version 1.0
 * @Desc
 */
public class LocalDateObjectConvert implements ObjectConvert<LocalDateTime> {
    @Override
    public LocalDateTime convert(Object object) {
        if (object == null) {
            return null;
        }
        String str = String.valueOf(object);
        if (str.length() > 19) {
            str = str.substring(0, 19);
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(str, df);
        return ldt;
    }
}
