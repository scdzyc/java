package com.scdzyc.springcloud.tools;

import org.reactivestreams.Publisher;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

/**
 *  代理模式
 * BodyHackerFunction class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud.tools
 * @blame yanchuan
 * @since 20/04/20 10:33
 */
public interface BodyHackerFunction extends
        BiFunction<ServerHttpResponse, Publisher<? extends DataBuffer>, Mono<Void>> {
}
