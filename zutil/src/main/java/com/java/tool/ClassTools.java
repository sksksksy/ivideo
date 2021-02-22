package com.java.tool;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * 类字节操作类
 *
 * @author zhoup
 */
@Slf4j
public class ClassTools {
    /**
     * 获取类中的所有属性名
     *
     * @param clazz    传入的类
     * @param result   属性结果集
     * @param isSupper 是否获取父类
     * @return
     */
    public final static List<String> getFields(Class<?> clazz, List<String> result, boolean isSupper) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            result.add(field.getName());
        }
        if (clazz.getSuperclass() == Object.class || !isSupper) {
            return result;
        }
        return getFields(clazz.getSuperclass(), result, true);
    }

    /**
     * 递归获取类及父类中的所有的属性名
     *
     * @param clazz
     * @param result
     * @return
     */
    public final static List<String> getFields(Class<?> clazz, List<String> result) {
        return getFields(clazz, result, true);
    }

    /**
     * 获取类的属性名和类型
     *
     * @param clazz
     * @param result
     * @param fieldType
     * @param isSupper  是否读取父类
     * @return
     */
    public final static void getFieldAndType(Class<?> clazz, List<String> result, Map<String, Class<?>> fieldType, boolean isSupper) {
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            result.add(field.getName());
            fieldType.put(field.getName(), field.getDeclaringClass());
        }
        if (clazz.getSuperclass() == Object.class || !isSupper) {
            return;
        }
        getFieldAndType(clazz.getSuperclass(), result, fieldType, true);
    }

    /**
     * 递归读取类及所有父类的属性名和类型
     *
     * @param clazz
     * @param result
     * @param fieldType
     */
    public final static void getFieldAndType(Class<?> clazz, List<String> result, Map<String, Class<?>> fieldType) {
        getFieldAndType(clazz, result, fieldType, true);
    }

    /**
     * @param obj
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public final static <T> T CheckNullAndCreate(T obj, Class<T> tClass) {
        if (obj == null) {
            try {
                return tClass.newInstance();
            } catch (InstantiationException e) {
                log.error("实例化错误", e);
            } catch (IllegalAccessException e) {
                log.error("非法参数", e);
            }
        }
        return obj;
    }
}
