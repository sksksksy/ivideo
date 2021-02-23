package com.java.jwt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 简单的jwt验证
 */
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    private JwtToken jwtToken;

    public TokenInterceptor(JwtToken jwtToken) {
        this.jwtToken = jwtToken;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Objects.requireNonNull(jwtToken, "jwtToken工具对象不能为空");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        TokenOfJwt tokenOfJwt = handlerMethod.getMethod().getAnnotation(TokenOfJwt.class);
        if (Objects.nonNull(tokenOfJwt)) {
            String tokenStr = request.getHeader(jwtToken.getTokenHeader());
            log.info("TokenString:{}", tokenStr);
            Objects.requireNonNull(tokenStr, "token不能为空");
            if (tokenOfJwt.onlyExpire() && !jwtToken.isTokenExpired(tokenStr)) {
                log.info("token超时,请重新生成");
                return false;
            }
            if (tokenOfJwt.isRefresh()) {
                String newTokenStr = jwtToken.refreshHeadToken(tokenStr);
                log.info("刷新token,新token为:{}", newTokenStr);
                response.setHeader(jwtToken.getTokenHeader(), newTokenStr);
            }
        }
        return true;
    }
}
