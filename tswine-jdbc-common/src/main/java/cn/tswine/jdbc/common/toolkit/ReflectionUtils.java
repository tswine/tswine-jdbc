package cn.tswine.jdbc.common.toolkit;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 反射工具类
 *
 * @Author: silly
 * @Date: 2019/8/20 20:20
 * @Version 1.0
 * @Desc
 */
public class ReflectionUtils {
    // //判断是否有@TableField、@TableId注解

    private ReflectionUtils() {
    }

    /**
     * 创建实例:无参构造函数
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        return newInstance(clazz, null, null);
    }

    /**
     * 创建实例:构造函数传参
     *
     * @param clazz
     * @param params 构造函数参数
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz, Object[] params) {
        Class<?>[] paramClazz = null;
        if (params != null && params.length > 0) {
            paramClazz = new Class<?>[params.length];
            for (int i = 0; i < params.length; i++) {
                paramClazz[i] = params[i].getClass();
            }
        }
        return newInstance(clazz, paramClazz, params);
    }


    /**
     * 创建实例:构造函数传参
     *
     * @param clazz
     * @param paramClazz 执行构造函数参数类型
     * @param params
     * @param <T>
     * @return
     */
    public static <T> T newInstance(Class<T> clazz, Class<?>[] paramClazz, Object[] params) {
        try {
            Constructor constructor;
            if (params == null || params.length <= 0 || paramClazz == null || paramClazz.length <= 0) {
                constructor = clazz.getConstructor();
            } else {
                constructor = clazz.getConstructor(paramClazz);
            }
            //反射私有构造函数
            constructor.setAccessible(true);
            return (T) constructor.newInstance(params);
        } catch (Exception ex) {
            throw ExceptionUtils.tse(ex);
        }

    }


    /**
     * 获取所有 public get方法的值
     *
     * @param clazz
     * @param entity 实体
     * @return
     */
    public static Map<Field, Object> getAllMethodValue(Class<?> clazz, Object entity) {
        List<Field> fieldList = getFieldList(clazz);
        Map<Field, Object> mapMethodValue = Collections.emptyMap();
        if (CollectionUtils.isNotEmpty(fieldList)) {
            mapMethodValue = new HashMap<>();
            for (Field field : fieldList) {
                mapMethodValue.put(field, getMethodValue(clazz, entity, field.getName()));
            }
        }
        return mapMethodValue;
    }

    /**
     * 获取 public get方法的值
     *
     * @param clazz
     * @param entity 实体
     * @param str    属性字符串内容
     * @return
     */
    public static Object getMethodValue(Class<?> clazz, Object entity, String str) {
        Map<String, Field> fieldMaps = getFieldMap(clazz);
        try {
            if (CollectionUtils.isEmpty(fieldMaps)) {
                throw ExceptionUtils.tse("获取字段为空,clazz=%s,str=%s", clazz.getSimpleName(), str);
            }
            //获取方法
            Method method = clazz.getMethod(getMethodCapitalize(fieldMaps.get(str), str));
            //执行方法
            return method.invoke(entity);
        } catch (Exception e) {
            throw ExceptionUtils.tse(e);
        }
    }

    /**
     * 反射 method方法名，例如 getXxx
     *
     * @param field 字段
     * @param str   属性字符串内容
     */
    public static String getMethodCapitalize(Field field, final String str) {
        Class<?> fieldType = field.getType();
        return StringUtils.concatCapitalize(boolean.class.equals(fieldType) ? "is" : "get", str);
    }


    /**
     * 获取该类的所有属性列表
     *
     * @param clazz 反射类
     * @return
     */
    public static Map<String, Field> getFieldMap(Class<?> clazz) {
        List<Field> fieldList = getFieldList(clazz);
        Map<String, Field> fieldMap = Collections.emptyMap();
        if (CollectionUtils.isNotEmpty(fieldList)) {
            fieldMap = new LinkedHashMap<>();
            for (Field field : fieldList) {
                fieldMap.put(field.getName(), field);
            }
        }
        return fieldMap;
    }

    /**
     * 获取该类的所有属性列表
     *
     * @param clazz 反射类
     * @return
     */
    public static List<Field> getFieldList(Class<?> clazz) {
        if (null == clazz) {
            return null;
        }
        List<Field> fieldList = new LinkedList<>();
        //获取所有字段
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            //过滤静态属性
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            //过滤 transient关键字修饰的属性
            if (Modifier.isTransient(field.getModifiers())) {
                continue;
            }
            fieldList.add(field);
        }
        //处理父类属性字段
        Class<?> superClass = clazz.getSuperclass();
        //所有对象的最终父类都为Object
        if (superClass.equals(Object.class)) {
            return fieldList;
        }
        //排除重载属性
        return excludeOverrideSuperField(fieldList, getFieldList(superClass));
    }


    /**
     * 排除重载父类属性
     *
     * @param fieldList      子类属性
     * @param superFieldList 父类属性
     * @return
     */
    public static List<Field> excludeOverrideSuperField(List<Field> fieldList, List<Field> superFieldList) {
        Map<String, Field> fieldMap = new HashMap<>();
        //子类属性
        for (Field field : fieldList) {
            fieldMap.put(field.getName(), field);
        }
        //父类属性
        for (Field superField : superFieldList) {
            if (null == fieldMap.get(superField.getName())) {
                //加入父类属性
                fieldList.add(superField);
            }
        }
        return fieldList;
    }

}

