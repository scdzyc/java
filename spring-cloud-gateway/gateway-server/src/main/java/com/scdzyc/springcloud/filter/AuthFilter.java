package com.scdzyc.springcloud.filter;

import com.scdzyc.springcloud.constant.AuthResponseCode;
import com.scdzyc.springcloud.entity.Account;
import com.scdzyc.springcloud.service.AuthService;
import com.scdzyc.springcloud.service.GatewayAuthService;
import com.scdzyc.springcloud.tools.AuthResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

/**
 *  网关授权验证过滤器 全局过滤器
 * AuthFilter class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud.filter
 * @blame yanchuan
 * @since 20/04/20 09:42
 */
@Component
@Slf4j
public class AuthFilter implements /*GatewayFilter,*/ GlobalFilter, Ordered {

    private static final String AUTH = "Authorization";
    private static final String USERNAME = "icodingedu-username";

    @Resource
    private AuthService gatewayAuthService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*********** Auth start ***********");
        // 获取request
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求头
        HttpHeaders headers = request.getHeaders();
        // 获取用户及token
        String token = headers.getFirst(AUTH);
        String userName = headers.getFirst(USERNAME);
        // 获取响应
        ServerHttpResponse response = exchange.getResponse();
        // 没有token信息
        if(StringUtils.isBlank(token) || StringUtils.isBlank(userName)) {
            log.error("token not found or username not found");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }
        // 验证
        Account account = Account.builder()
                .token(token)
                .userName(userName)
                .build();
        AuthResponse verify = gatewayAuthService.verify(account);
        if(!AuthResponseCode.SUCCESS.equals(verify.getCode())) {
            log.error("invalid token");
            response.setStatusCode(HttpStatus.FORBIDDEN);
            return response.setComplete();
        }
        // 将用户信息在此处存放在请求的header中传递给下游
        ServerHttpRequest buildRequest = request.mutate()
                .header(USERNAME, userName)
                .header(AUTH, token)
                .build();

        response.setStatusCode(HttpStatus.OK);
        // 在响应中添加返回数据
        response.getHeaders().add("icoding","教育");
        return chain.filter(exchange.mutate()
                    .request(buildRequest)
                    .response(response)
                    .build());
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
