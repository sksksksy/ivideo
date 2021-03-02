package com.java.spring.limit;

import com.java.cache.CacheOfSelf;
import com.java.cache.self.CacheServer;
import com.java.exception.BaseRunException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 分流拦截器
 */
@Slf4j
public class AccessLimitInterceptor implements HandlerInterceptor {
    /**
     * 增加连接数，和计算是否超出限制
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
        String name = ((HandlerMethod) handler).getMethod().getName();
        CacheOfSelf cacheOfSelf = new CacheServer();
        Integer value = cacheOfSelf.getValue(Integer.class, name);
        if (Objects.nonNull(value)) {
            cacheOfSelf.setKeyValue(name, value + 1);
        } else {
            cacheOfSelf.setKeyValue(name, Integer.valueOf(0));
            value = 0;
        }
        int result = value / accessLimit.seconds();
        if (result > accessLimit.maxCount()) {
            BaseRunException.throwException("超出限制了 现在平均量为:" + result + " 最大量为:" + accessLimit.maxCount());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 处理完成，减少连接数
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        String name = ((HandlerMethod) handler).getMethod().getName();
        CacheOfSelf cacheOfSelf = new CacheServer();
        Integer value = cacheOfSelf.getValue(Integer.class, name);
        cacheOfSelf.setKeyValue(name, value - 1);
    }
}
