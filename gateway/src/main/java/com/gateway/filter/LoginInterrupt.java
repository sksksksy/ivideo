package com.gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * 准备写登录验证逻辑
 */
public class LoginInterrupt implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        boolean isError = false;
        if (isError) {
            return Mono.empty();
        }
        return webFilterChain.filter(serverWebExchange);
    }
}
