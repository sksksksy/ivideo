package com.java.jwt;

import java.lang.annotation.*;

/**
 * 如果被该注解标记，标识该方法需要被Token校验
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TokenOfJwt {
    /**
     * 是否只校验id
     * @return
     */
    boolean onlyExpire() default true;

    /**
     * 是否刷新Token
     * @return
     */
    boolean isRefresh() default true;
}
