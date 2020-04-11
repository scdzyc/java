package com.scdzyc.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 启用断路器
@EnableCircuitBreaker
// 启用eureka注册
@EnableDiscoveryClient
// 启用feign
@EnableFeignClients
@SpringBootApplication
public class FeignHystrixFallbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignHystrixFallbackApplication.class, args);
    }
}
