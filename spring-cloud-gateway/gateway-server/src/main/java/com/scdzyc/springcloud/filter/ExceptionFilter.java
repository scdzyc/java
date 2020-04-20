package com.scdzyc.springcloud.filter;

import com.scdzyc.springcloud.tools.BodyHackerFunction;
import com.scdzyc.springcloud.tools.BodyHackerHttpResponseDecorator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 *  统一异常处理 {@link GlobalFilter} 全局过滤器
 * ExceptionFilter class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud.filter
 * @blame yanchuan
 * @since 20/04/20 10:41
 */
@Slf4j
@Component
public class ExceptionFilter implements GlobalFilter, /*GatewayFilter,*/ Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        final ServerHttpRequest request = exchange.getRequest();
        // TODO 这里定义写入Body的逻辑
        BodyHackerFunction delegate = (resp, body) -> Flux.from(body)
                .flatMap(orgBody -> {
                    byte[] orgContent = new byte[orgBody.readableByteCount()];
                    orgBody.read(orgContent);
                    String content = new String(orgContent);
                    log.info("original content {}", content);

                    // 如果500 错误，替换
                    if(resp.getStatusCode().value() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                        content = String.format("{\"status\":%d,\"path\":\"%s\"}",
                                resp.getStatusCode().value(), request.getPath().value());
                    }
                    // 告知客户端body的长度（如果不设置的客户端会一直处于等待状态不结束）
                    HttpHeaders headers = resp.getHeaders();
                    headers.setContentLength(content.length());
                    return resp.writeWith(Flux.just(content)
                        .map(bx -> resp.bufferFactory().wrap(bx.getBytes())));
                }).then();

        // 将装饰器当做Response 返回
        BodyHackerHttpResponseDecorator responseDecorator = new BodyHackerHttpResponseDecorator(delegate, exchange.getResponse());

        return chain.filter(exchange.mutate().response(responseDecorator).build());
    }

    @Override
    public int getOrder() {
        //  // WRITE_RESPONSE_FILTER的执行顺序是-1，我们的Hacker在它之前执行
        return -2;
    }
}
