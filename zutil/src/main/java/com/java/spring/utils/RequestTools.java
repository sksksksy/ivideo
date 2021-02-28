package com.java.spring.utils;

import com.java.exception.BaseRunException;
import com.java.tool.ClassTools;
import com.java.tool.IoTools;
import com.sun.istack.internal.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ClassUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.annotation.Nonnull;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
        BaseRunException.check(f -> f != null, function, "函数式不能为空");
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

    /**
     * 检查对象中是否有属性值为空字符串，若有则置为null
     *
     * @param t
     * @param <T>
     * @return
     */
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
     * 剔除换行符
     *
     * @param s not null
     * @return
     */
    public static final String trimLineBreak(@Nonnull String s) {
        return s.replaceAll("\\s{1,}|\t|\r|\n", "");
    }

    /**
     * 低效率剔除空白字符和0
     *
     * @param s not null
     * @return
     */
    @Deprecated
    public static final String trimBlank(@Nonnull String s) {
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

    /**
     * spring mvc里面的获取multiParts
     * multipart/form-data上传类型
     *
     * @param request
     * @return
     * @throws IOException
     * @throws ServletException
     */
    public static final List<MultipartFile> getMultiParts(HttpServletRequest request) throws IOException, ServletException {
        Objects.requireNonNull(request, "请求信息不能为空");
        List<MultipartFile> multipartFileList = new LinkedList<>();
        StandardMultipartHttpServletRequest fileRequest = (StandardMultipartHttpServletRequest) request;
        MultiValueMap<String, MultipartFile> multiFileMap = fileRequest.getMultiFileMap();
        Objects.requireNonNull(multiFileMap, "请求参数不能为空");
        for (Map.Entry<String, List<MultipartFile>> entrys : multiFileMap.entrySet()) {
            String key = entrys.getKey();
            log.info("key值:" + key);
            List<MultipartFile> multipartFiles = entrys.getValue();
            for (MultipartFile file : multipartFiles) {
                String originalFilename = file.getOriginalFilename();
                log.info("fileName:" + originalFilename);
                multipartFileList.add(file);
            }
        }
        return multipartFileList;
    }

    /**
     * 通过Request提供的方法获取文件上传部分
     * multipart/form-data上传类型
     *
     * @param request
     * @return
     */
    public static final Collection<Part> getParts(HttpServletRequest request) {
        Collection<Part> parts = null;
        try {
            parts = request.getParts();
        } catch (IOException e) {
            BaseRunException.throwException("获取part内容IO异常", e);
        } catch (ServletException e) {
            BaseRunException.throwException("获取part内容ServletException异常", e);
        }
        BaseRunException.check(parts == null || parts.size() == 0, "parts为空");
        return parts;
    }

    /**
     * 只打印打印上传文件的信息
     *
     * @param request
     */
    public static void printPartInfo(HttpServletRequest request) {
        Collection<Part> parts = getParts(request);
        parts.stream().forEach(part -> {
            String contentType = part.getContentType();
            long size = part.getSize();
            String name = part.getName();
            String submittedFileName = part.getSubmittedFileName();
            System.out.println("[contentType is ]" + contentType + " [size is]" + size + " [name is]" + name + " [submittedFileName is]" + submittedFileName);
        });
    }
}
