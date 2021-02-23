package com.java.tool;

import com.java.exception.BaseRunException;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Function;

@Slf4j
public class RequestTools {


    /**
     * 根据对象，获取参数注入到对象中,只能利用getParameter取值
     *
     * @param t
     * @param request
     * @param <T>
     * @return
     */
    public final static <T> T getParameter(T t, HttpServletRequest request) {
        if (t == null) return null;
        Class<?> clazz = t.getClass();
        List<String> fields = new LinkedList<>();
        Map<String, Class<?>> fieldType = new LinkedHashMap<>();
        ClassTools.getFieldAndType(clazz, fields, fieldType);
        fields.forEach(
                e -> {
                    Method method = ClassUtils.getMethod(clazz, "set" + StringUtils.capitalize(e), fieldType.getOrDefault(e, null));
                    if (Objects.nonNull(method)) {
                        String a = request.getParameter(e);
                        if (a != null) {
                            try {
                                if (method.getParameterTypes()[0] == Integer.class) {
                                    method.invoke(t, Integer.parseInt(a));
                                } else {
                                    method.invoke(t, a);
                                }
                            } catch (IllegalAccessException illegalAccessException) {
                                log.error("非法", illegalAccessException);
                            } catch (InvocationTargetException invocationTargetException) {
                                log.error("invocation", invocationTargetException);
                            }
                        }
                    }
                }
        );
        return t;
    }

    /**
     * 灵活可扩展的获取实体类
     *
     * @param t
     * @param function
     * @param <R>
     * @param <P>
     * @param <T>
     * @return
     */
    public final static <R extends String, P extends String, T> T getParameter(T t, Function<P, R> function) {
        if (t == null) return null;
        Class<?> clazz = t.getClass();
        List<String> fields = new LinkedList<>();
        Map<String, Class<?>> fieldType = new LinkedHashMap<>();
        ClassTools.getFieldAndType(clazz, fields, fieldType);
        fields.forEach(
                e -> {
                    Method method = ClassUtils.getMethod(clazz, "set" + StringUtils.capitalize(e), fieldType.getOrDefault(e, null));
                    if (Objects.nonNull(method)) {
                        String a = function.apply((P) e);
                        if (a != null) {
                            try {
                                if (method.getParameterTypes()[0] == Integer.class) {
                                    method.invoke(t, Integer.parseInt(a));
                                } else {
                                    method.invoke(t, a);
                                }
                            } catch (IllegalAccessException illegalAccessException) {
                                BaseRunException.throwException("获取请求参数时出错", illegalAccessException);
                                log.error("非法", illegalAccessException);
                            } catch (InvocationTargetException invocationTargetException) {
                                BaseRunException.throwException("获取请求参数时出错", invocationTargetException);
                            }
                        }
                    }
                }
        );
        return t;
    }

    public final static <T> T EmptyToNullInObject(@NotNull T t) {
        Class<?> clazz = t.getClass();
        List<String> fields = new LinkedList<>();
        Map<String, Class<?>> fieldType = new LinkedHashMap<>();
        ClassTools.getFieldAndType(clazz, fields, fieldType);
        for (String e : fields) {
            Method getMethod = ClassUtils.getMethod(clazz, "get" + StringUtils.capitalize(e), null);
            try {
                Object obj = getMethod.invoke(t, null);
                if (obj instanceof String) {
                    String s = (String) obj;
                    if ("".equals(s)) {
                        Method setMethod = ClassUtils.getMethod(clazz, "set" + StringUtils.capitalize(e), fieldType.getOrDefault(e, null));
                        if (Objects.nonNull(setMethod)) {
                            Field field = clazz.getDeclaredField(e);
                            field.setAccessible(true);
                            field.set(t, null);
                        }
                    }
                }
            } catch (IllegalAccessException ex) {
                BaseRunException.throwException("检测对象是否有空字符串时出错", ex);
            } catch (InvocationTargetException ex) {
                BaseRunException.throwException("检测对象是否有空字符串时出错", ex);
            } catch (NoSuchFieldException ex) {
                BaseRunException.throwException("检测对象是否有空字符串时出错", ex);
            }
        }
        return t;
    }

    /**
     * 低效率剔除空白字符
     *
     * @param s 字符
     * @return
     */
    @Deprecated
    public static final String trimBlank(String s) {
        s = s.replaceAll("\\s{1,}|\t|\r|\n", "");
        byte[] bytes = s.getBytes();
        int n = 0;
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        for (int i = 0; i < bytes.length; i++) {
            if ((bytes[i] & 0xff) != 0) {
                byteBuffer.write(bytes[i]);
            } else {
                n++;
            }
        }
        byte[] bs = byteBuffer.toByteArray();
        if (bs.length < bytes.length) {
            s = new String(bs);
            log.info("剔除：" + n + "个元素");
        }
        IoTools.shutdownOutputStream(byteBuffer);
        return s;
    }
}
