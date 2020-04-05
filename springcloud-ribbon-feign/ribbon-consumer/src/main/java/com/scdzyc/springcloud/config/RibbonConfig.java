package com.scdzyc.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// 这样可以对指定服务进行指定负载均衡策略
// @RibbonClient(name = "eureka-client",configuration = com.netflix.loadbalancer.RandomRule.class)
@Configuration
public class RibbonConfig {

    /**
     * 全局的负载均衡策略
     * @return
     */
    @Bean
    public IRule globlaIRule() {

        // 轮询是默认的
        return new RoundRobinRule();
        // 多种策略可选
    }
}
