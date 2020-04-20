package com.scdzyc.springcloud.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

/**
 * RedisLimiterConfig class
 *
 * @author yanchuan
 * @module com.scdzyc.springcloud.config
 * @blame yanchuan
 * @since 20/04/20 11:03
 */
@Configuration
public class RedisLimiterConfig {

    /**
     *      ID: KEY 限流的业务标识
     *      我们这里根据用户请求IP地址进行限流
     * @return
     */
    @Bean
    @Primary // 主要的 系统不止有一个KeyResolver
    public KeyResolver remoteAddressKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                    .getRemoteAddress()
                    .getAddress()
                    .getHostAddress()
        );
    }

    @Bean("redisRateLimiterUser")
    @Primary
    public RedisRateLimiter redisRateLimiterUser() {
        // 这里可以自己创建一个限流脚本,也可以使用默认的令牌桶
        //defaultReplenishRate:限流桶速率,每秒10个
        //defaultBurstCapacity:桶的容量 60
        return new RedisRateLimiter(10,60);
    }

    public RedisRateLimiter redisRateLimiterProduct() {
        //这里可以自己创建一个限流脚本,也可以使用默认的令牌桶
        //defaultReplenishRate:限流桶速率,每秒20个
        //defaultBurstCapacity:桶的容量 100
        return new RedisRateLimiter(20,100);
    }
}
