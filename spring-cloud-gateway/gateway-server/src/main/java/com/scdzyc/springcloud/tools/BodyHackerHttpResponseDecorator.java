package com.scdzyc.springcloud.tools;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import reactor.core.publisher.Mono;

/**
 *  装饰器模式
 * BodyHackerHttpResponseDecorator class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud.tools
 * @blame yanchuan
 * @since 20/04/20 10:35
 */
public class BodyHackerHttpResponseDecorator extends ServerHttpResponseDecorator {

    /**
     * 负责具体写入Body内容的代理
     */
    private BodyHackerFunction delagate;

    public BodyHackerHttpResponseDecorator(BodyHackerFunction bodyHandler, ServerHttpResponse delegate) {
        super(delegate);
        this.delagate = bodyHandler;
    }

    @Override
    public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
        return delagate.apply(getDelegate(), body);
    }
}
