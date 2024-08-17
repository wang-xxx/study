package com.demo.gateway.filter;

import com.demo.gateway.config.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Order(value = -1)
@Component
public class AuthorizeFilter implements GlobalFilter {

    @Autowired
    private AuthProperties jwtProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        // 是否需要登录验证
        if (exclude(request.getPath().toString())) {
            // 放行
            return chain.filter(exchange);
        }
        // 获取请求头token
        HttpHeaders headers = request.getHeaders();
        List<String> authorizationList = headers.get("authorization");
        String token = null;
        if (authorizationList != null && authorizationList.size() > 0) {
            token = authorizationList.get(0);
        }
        // 校验token获取用户id
        Long userId = null;
        try {
            userId = JWTParse(token);
        } catch (Exception e) {
            // 拦截
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 传递用户信息
        String userInfo = userId.toString();
        ServerWebExchange newExchange = exchange.mutate().request(builder -> {
            builder.header("userInfo", userInfo);
        }).build();
        return chain.filter(newExchange);
    }

    /**
     * 登录路径校验
     *
     * @param path
     * @return true 放行，false 拦截验证
     */
    private boolean exclude(String path) {
        List<String> excludePaths = jwtProperties.getExcludePaths();
        if (excludePaths != null) {
            return excludePaths.stream().anyMatch(pathPattern -> antPathMatcher.match(pathPattern, path));
        }
        return false;
    }

    private Long JWTParse(String token) {
        // TODO JWT验证token
        return 1L;
    }

}
