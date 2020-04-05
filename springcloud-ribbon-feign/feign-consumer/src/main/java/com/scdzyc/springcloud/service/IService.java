package com.scdzyc.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//eureka服务提供者的service-name
//这个注解的意思是IService这个接口的调用都发到eureka-client这个服务提供者上
@FeignClient("eureka-client")
public interface IService {

    @GetMapping("/sayhello")
    String hello();
}
