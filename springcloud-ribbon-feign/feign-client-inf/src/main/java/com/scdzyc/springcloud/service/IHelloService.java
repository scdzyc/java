package com.scdzyc.springcloud.service;

import com.scdzyc.springcloud.pojo.ServerInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("feign-client")
public interface IHelloService {

    @GetMapping("/sayhello")
    ServerInfo sayHello();

    @PostMapping("/sayhello")
    ServerInfo sayHello(@RequestBody ServerInfo serverInfo);
}
