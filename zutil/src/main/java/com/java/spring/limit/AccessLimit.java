package com.java.spring.limit;

import java.lang.annotation.*;

/**
 * 作用于接口之上
 * 判断依据
 * 当前接口请求数/单位秒 > maxCount
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface AccessLimit {
    /**
     * 接口名称
     *
     * @return
     */
    String name() default "";

    /**
     * 单位秒
     *
     * @return
     */
    int seconds() default 1;

    /**
     * 单位秒内的请求数
     *
     * @return
     */
    int maxCount();

    /**
     * 是否需要登录
     *
     * @return
     */
    boolean needLogin() default true;


}
