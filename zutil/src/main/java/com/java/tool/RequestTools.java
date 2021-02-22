package com.java.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class RequestTools {


    /**
     * 根据对象，获取参数注入到对象中
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
